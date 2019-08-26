import React from 'react';
import logo from './logo.svg';
import './App.css';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Hourlist from './components/Hourlist';


function App() {
  return (
    <div className="App">
      < AppBar position=" static" color=" default" >
        <toolbar>
          <typography variant="h6" color="inherit" >
            hourlist
          </typography>
        </toolbar>
      </AppBar>
      <hourlist/>
    </div>
  );
}

export default App;
