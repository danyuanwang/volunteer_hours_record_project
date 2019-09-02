import React, { Component } from "react";
import { SERVER_URL } from "../constants.js";
import ReactTable from "react-table";
import "react-table/react-table.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import AddHour from "./AddHours";
import EditHour from "./EditHours";
import Grid from "@material-ui/core/Grid";
import { CSVLink } from "react-csv";

class Hourlist extends Component {
  constructor(props) {
    super(props);
    this.state = { hours: [] };
  }
  componentDidMount() {
    this.fetchHours();
  }
  fetchHours = () => {
    const token = sessionStorage.getItem("jwt");
    fetch(SERVER_URL + "api/hourses", {
      headers: { Authorization: token }
    })
      .then(response => response.json())
      .then(responsedata => {
        this.setState({
          hours: responsedata._embedded.hourses
        });
      })
      .catch(err => console.error(err));
  };
  //no deleting permanant record
  onDelClick = link => {
    if (window.confirm("are you sure to delete?")) {
      const token = sessionStorage.getItem("jwt");
      fetch(link, {
        method: "DELETE",
        headers: { Authorization: token }
      })
        .then(res => {
          toast.success("hour deleted", {
            position: toast.POSITION.BOTTOM_LEFT
          });
          this.fetchHours();
        })

        .catch(err => {
          toast.error("error when deleting", {
            position: toast.POSITION.BOTTOM_LEFT
          });
          console.error(err);
        });
    }
  };
  addHour(hours) {
    const token = sessionStorage.getItem("jwt");
    fetch(SERVER_URL + "api/hourses", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: token
      },
      body: JSON.stringify(hours)
    })
      .then(res => this.fetchHours())
      .catch(err => console.error(err));
  }
  updateHour(hour, link) {
    const token = sessionStorage.getItem("jwt");
    fetch(link, {
      method: "PUT",
      headers: { "Content-Type": "application/json", Authorization: token },
      body: JSON.stringify(hour)
    })
      .then(res => {
        toast.success("Changes saved", {
          position: toast.POSITION.BOTTOM_LEFT
        });
        this.fetchHours();
      })
      .catch(err =>
        toast.error("Error when saving", {
          position: toast.POSITION.BOTTOM_LEFT
        })
      );
  }

  /*render(){
        const tableRows = this.state.hours.map(( hour, index) =>
        <tr key={index}> */
  render() {
    const columns = [
      { Header: "Hours", accessor: "durationHours" },
      { Header: "Email", accessor: "confirmationEmail" },
      { Header: "Date", accessor: "date" },
      {
        id: "delbutton",
        sortable: false,
        filterable: false,
        width: 100,
        accessor: "_links.self.href",
        Cell: ({ value, row }) => (
          <EditHour
            hour={row}
            link={value}
            updateHour={this.updateHour}
            fetchHours={this.fetchHours}
          />
        )
      },
      {
        sortable: false,
        filterable: false,
        width: 100,
        accessor: "_links.self.href",
        Cell: ({ value }) => (
          <button
            onClick={() => {
              this.onDelClick(value);
            }}
          >
            {" "}
            Delete{" "}
          </button>
        )
      }
    ];
    //  </tr>

    return (
      <div classname="App">
        <Grid container>
          <Grid item>
            <AddHour addHour={this.addHour} fetchHours={this.fetchHours} />
          </Grid>
          <Grid item style={{ padding: 15 }}>
            <CSVLink data={this.state.hours} separator=";">
              {" "}
              Export CSV{" "}
            </CSVLink>
          </Grid>
        </Grid>
        <ReactTable
          data={this.state.hours}
          columns={columns}
          filterable={true}
        />
        <ToastContainer autoClose={1500} />
      </div>
    );
  }
}
export default Hourlist;
