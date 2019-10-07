import React, { Component } from "react";
import { Route, withRouter, Switch } from "react-router-dom";
import "./App.css";
import { Layout, notification } from "antd";
import Hourlist from "./components/Hourlist"
import AddHours from "./components/AddHours"
import AppHeader from "./common/AppHeader";
import NotFound from "./common/NotFound"
import PrivateRoute from "./common/PrivateRoute"
import LoadingIndicator from "./common/LoadingIndicator";
import Signup from "./user/Signup"
import Profile from "./user/Profile"
import Login from "./user/login";
import { getCurrentUser } from "./util/APIUtils";
import { ACCESS_TOKEN } from './constants';

const { Content } = Layout;

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      currentUser: null,
      isAuthenticated: false,
      isLoading: false
    };
    this.handleLogout = this.handleLogout.bind(this);
    this.loadCurrentUser = this.loadCurrentUser.bind(this);
    this.handleLogin = this.handleLogin.bind(this);

    notification.config({
      placement: "topRight",
      top: 70,
      duration: 3
    });
  }

  loadCurrentUser() {
    this.setState({
      isLoading: true
    });
    getCurrentUser()
      .then(response => {
        this.setState({
          currentUser: response,
          isAuthenticated: true,
          isLoading: false
        });
      })
      .catch(error => {
        this.setState({
          isLoading: false
        });
      });
  }

  componentDidMount() {
    this.loadCurrentUser();
  }

  handleLogout(redirectTo="/", notificationType="success", description="You're successfully logged out.") {
    localStorage.removeItem(ACCESS_TOKEN);

    this.setState({
      currentUser: null,
      isAuthenticated: false
    });

    this.props.history.push(redirectTo);
    
    notification[notificationType]({
      message: 'Polling App',
      description: description,
    });
  }

  handleLogin() {
    this.loadCurrentUser();
    this.props.history.push("/");
  }

  render() {
    if (this.state.isLoading) {
      return <LoadingIndicator />;
    }
    return (
      <Layout className="app-container">
        <AppHeader
          isAuthenticated={this.state.isAuthenticated}
          currentUser={this.state.currentUser}
          onLogout={this.handleLogout}
        />
        <Content className="app-content">
          <div className="container">
            <Switch>
              <PrivateRoute
                exact
                path="/"
                render={props => (
                  <Hourlist
                    isAuthenticated={this.state.isAuthenticated}
                    currentUser={this.state.currentUser}
                    handleLogout={this.handleLogout}
                    {...props}
                  />
                )}
              ></PrivateRoute>
              <Route
                path="/login"
                render={props => (
                  <Login onLogin={this.handleLogin} {...props} />
                )}
              ></Route>
              <Route path="/signup" component={Signup}></Route>
              <Route
                path="/users/:username"
                render={props => (
                  <Profile
                    isAuthenticated={this.state.isAuthenticated}
                    currentUser={this.state.currentUser}
                    {...props}
                  />
                )}
              ></Route>
              <PrivateRoute
                authenticated={this.state.isAuthenticated}
                path="/AddHours"
                component={AddHours}
                handleLogout={this.handleLogout}
              ></PrivateRoute>
              <Route component={NotFound}></Route> 
            </Switch>
          </div>
        </Content>
      </Layout>
    );
  }
}
export default withRouter(App);
