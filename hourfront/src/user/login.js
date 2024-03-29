import React, { useState } from "react";
import { SERVER_URL } from "../constants.js";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Link from "@material-ui/core/Link";
import Hourlist from "../components/Hourlist";
import { ToastContainer, toast } from "react-toastify"
import "react-toastify/dist/ReactToastify.css";
import './login.css';

import { makeStyles } from '@material-ui/core/styles'
const useStyles = makeStyles(theme => ({
  button: {
    margin: theme.spacing(1),
  },
  link: {
    margin: theme.spacing(1),  
  },
  input: {
    display: 'none',
  },
}));

const Login = () => {
  const classes = useStyles();
  const [user, setUser] = useState({ username: "", password: "" });
  const [isAuthenticated, setAuth] = useState(false);
  const handleChange = event => {
    setUser({ ...user, [event.target.name]: event.target.value });
  };

  const logout = () => {
    sessionStorage.removeItem("jwt");
    setAuth(false);
  };

  const login = () => {
    fetch(SERVER_URL + "/auth/signin", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json"
      },
      body: JSON.stringify(user)
    })
      .then(res => {
        const jwtToken = res.headers.get("Authorization");
        if (jwtToken !== null) {
          sessionStorage.setItem("jwt", jwtToken);
          setAuth(true);
        } else {
          toast.warn("Check your username and password", {
            position: toast.POSITION.BOTTOM_LEFT
          });
        }
      })
      .catch(err => console.error(err));
  };
  if (isAuthenticated === true) {
    return (
      <div>
        <Hourlist />
      </div>
    );
  } else {
    return (
      <div  className="login-container">
        <TextField name="username" label="Username" onChange={handleChange} />
        <br />
        <TextField
          type="password"
          name="password"
          label="Password"
          onChange={handleChange}
        />
        <br />
        <br />
        <Button variant="outlined" color="primary" className={classes.button} onClick={login}>
          Login
        </Button>
        <Link href='/signup' color="inherit" className={classes.link}>
          signup
        </Link>
        <ToastContainer autoClose={1500} />
      </div>
    );
  }
};
export default Login;
