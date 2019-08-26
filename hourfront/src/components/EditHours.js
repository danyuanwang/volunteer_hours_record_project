import React, { useState } from 'react';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/ core/ DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import TextField from '@material-ui/core/TextField';


const EditHours = (props) => {

    const [open, setOpen] = useState(false);
    const [hour, setHour] = useState({
        hours: '', email: '', date: '', user: ''
    });
    const handleClickOpen = () => { setOpen(true); };
    // Close the modal form 
    const handleClose = () => { setOpen(false); };
    const handleChange = (event) => { setHour({ ...hour, [event.target.name]: event.target.value }); }
    const handleSave = () => { props.AddHours(hour); handleClose(); }
    return (
        <div>
            < Button color="primary" size="small" onClick={handleClickOpen} > Edit </ Button >

            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>new hours</DialogTitle>
                <DialogContent>
                < TextField autoFocus fullWidth label ="Hours" name ="hours"
                    value ={ hour.hours} onChange ={ handleChange}/>
                < TextField autoFocus fullWidth label ="Email" name ="email"
                    value ={ hour.email} onChange ={ handleChange}/>
                < TextField autoFocus fullWidth label ="Date" name ="date"
                    value ={ hour.date} onChange ={ handleChange}/>
                < TextField autoFocus fullWidth label ="User" name ="user"
                    value ={ hour.user} onChange ={ handleChange}/>
                </ DialogContent >
                < DialogActions >
                    < Button color=" secondary" onClick={handleClose} > Cancel </ Button >
                    < Button color=" primary" onClick={handleSave} > Save </ Button >

                </ DialogActions >
            </ Dialog >


        </div>
    );
};
export default AddHours;