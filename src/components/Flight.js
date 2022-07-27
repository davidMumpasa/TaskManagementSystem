import * as React from 'react';
import {useState} from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Paper from '@mui/material/Paper';
import axios from "axios";


export default function Flight() {
    //const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const [name, setName] = useState('')
    const [description, setDescription] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [tasks, setTasks] = useState([])
    const [owner, setowner] = useState('');


    const handleClick = (e) => {
        e.preventDefault()
        const task = {name,email,password, description, owner}

        axios.post("http://localhost:8080/home/createTask", task);

        //
        // fetch("http://localhost:8080/home/createTask",{
        //     method:'POST',
        //     Headers:{'Content-Type': 'application/json'},
        //     body:JSON.stringify(task)
        //
        // }).then(()=>{
        //     console.log("added")
        // })

    }


    React.useEffect(() => {
        fetch("http://localhost:8080/home/getAllTasks")
            .then(res => res.json())
            .then((result) => {
                    setTasks(result);
                }
            )
    }, [])


    return (
        <div style={{width: '100%'}}>
            <Box
                component="form"
                sx={{
                    '& > :not(style)': {m: 1, width: '25ch'},
                }}
                noValidate
                autoComplete="off"
            >

                <Paper elevation={3} style={{padding: '50px 20px', width: 600, margin: "20px auto"}}>
                    <from noValidate autoConplete="off">

                        <TextField margin="normal" id="outlined-basic" label="Username" variant="outlined" fullWidth
                                   value={name} onChange={(e) => setName(e.target.value)}/><br/>
                        <TextField margin="normal" id="outlined-basic" label="Email"  variant="outlined" fullWidth
                                   value={email} onChange={(e) => setEmail(e.target.value)}/><br/>
                        <TextField margin="normal" id="outlined-basic" label="Password"  variant="outlined" fullWidth
                                   value={password} onChange={(e) => setPassword(e.target.value)}/><br/>

                        <TextField margin="normal" id="outlined-basic" label="description" variant="outlined" fullWidth
                                   value={description} onChange={(e) => setDescription(e.target.value)}/><br/>
                        <TextField margin="normal" id="outlined-basic" label="owner" variant="outlined" fullWidth
                                   value={owner} onChange={(e) => setowner(e.target.value)}/>


                        <Button variant="contained" color="success" onClick={handleClick} fullWidth>
                            Submit
                        </Button>
                    </from>
                </Paper>

                {tasks.map(task => (
                    <Paper elevation={3} style={{padding: '30px 10px', width: 600, margin: "20px auto"}}>
                        <div>
                            Name:{task.name}<br/>
                            Description:{task.description}<br/>
                            owner:{task.owner}<br/>
                        </div>
                    </Paper>

                ))}
            </Box>

        </div>

    );
}
