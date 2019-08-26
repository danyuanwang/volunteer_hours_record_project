import React, { useState } from 'react';
import { SERVER_URL } from '../constants.js';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Hourlist from './HourList';


const Login = () => {
    const [user, setUser] = useState({ username: '', password: '' });
    const [isAuthenticated, setAuth] = useState(false);
    const handleChange = (events) => {
        setUser({ ...user, [event.target.name]: event.target.value })

    }
    const login = () => {
        fetch(SERVER_URL + 'login', {
            method: 'POST',
            body: JSON.stringify(user)
        })
            .then(res => {
                const jwtToken = res.headers.get('Authorization');
                if (jwtToken !== null) {
                    sessionStorage.setItem("jwt", jwtToken);
                    setAuth(true);

                }
            })
            .catch(err => console.error(err))
    }
    if (isAuthenticated === true) {
        return (<Hourlist />)
    }
    else {
        return (
            < div >
                <TextField name='username'
                    label="Username" onChange={handleChange} /><br />
                <TextField type='password'
                    label="Password" onChange={handleChange} /><br /><br />
                < Button variant="outlined" color="primary"
                    onClick={login} >
                    Login
            </ Button >
            </ div>
        );
    }
}
export default Login;