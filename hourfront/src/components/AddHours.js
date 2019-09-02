import React, { useState } from "react";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogTitle from "@material-ui/core/DialogTitle";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
const AddHours = props => {
  const [open, setOpen] = useState(false);
  const [hour, setHour] = useState({
    durationHours: "",
    confirmationEmail: "",
    date: ""
  });
  const handleClickOpen = props => {
    setOpen(true);
  };
  // Close the modal form
  const handleClose = () => {
    setOpen(false);
  };
  const handleChange = event => {
    setHour({ ...hour, [event.target.name]: event.target.value });
  };
  const handleSave = () => {
    props.addHour(hour);
    handleClose();
  };
  return (
    <div>
      <Button
        variant="outlined"
        color="primary"
        style={{ margin: 10 }}
        onClick={handleClickOpen}
      >
        {" "}
        New Hours{" "}
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>new hours</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            fullWidth
            label="Hours"
            name="durationHours"
            value={hour.hours}
            onChange={handleChange}
          />
          <TextField
            autoFocus
            fullWidth
            label="Email"
            name="confirmationEmail"
            value={hour.email}
            onChange={handleChange}
          />
          <TextField
            autoFocus
            fullWidth
            label="Date"
            name="date"
            value={hour.date}
            onChange={handleChange}
          />
        </DialogContent>
        <DialogActions>
          <Button color="secondary" onClick={handleClose}>
            {" "}
            Cancel{" "}
          </Button>
          <Button color="primary" onClick={handleSave}>
            {" "}
            Save{" "}
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};
export default AddHours;
