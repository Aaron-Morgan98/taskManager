import * as React from 'react';
import {useState} from "react";
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import axios from "axios";
import * as yup from "yup";
import {useNavigate} from "react-router-dom";

const Copyright = (props) => {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © '}
      <Link color="inherit" href="https://aaron-morgan.com/" rel="noopener noreferrer" target="_blank">
        aaron-morgan.com
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}


export const RegisterForm = () => {
    const navigate = useNavigate();


    const [formData, setFormData] = useState({
        username:"",
        team:"",
        email:"",
        password:"",
    });

    const [errors, setErrors] = useState({
        username:"",
        team:"",
        email:"",
        password:"",
    });

    const schema = yup.object().shape({
        username: yup.string().min(8).required("User name required - 8 characters minimum."),
        team: yup.string().required("Please enter your team."),
        email: yup.string().email("Invalid email.").required("Please enter your email."),
        password: yup.string().min(6).required("Please enter a valid password."),
        });


const handleChange = (e) => {
    setFormData({...formData, [e.target.name]: e.target.value});
};

const handleSubmit = async (e) => {
    e.preventDefault();
    try{
        await schema.validate(formData, {abortEarly:false});
        const response = await axios.post("http://127.0.0.1:8080/users/create", formData);
        console.log(response.data);
        navigate("/login");
        } catch(error){
        // display errors based on yup validation
            if(error instanceof yup.ValidationError){
                const validationErrors = {};
                error.inner.forEach(e => {
                    validationErrors[e.path] = e.message;
                });
                setErrors(validationErrors);
            }
            console.error("Error", error);
        }
    };


const defaultTheme = createTheme();

  return (
    <ThemeProvider theme={defaultTheme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign up
          </Typography>
          <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  name="username"
                  required
                  fullWidth
                  id="username"
                  label="User Name"
                  autoFocus
                  value={formData.username}
                  onChange={handleChange}
                  error={!!errors.username}
                  helperText={errors.username}
                />

              </Grid>
              <Grid item xs={12}>
                <TextField
                  name="team"
                  required
                  fullWidth
                  id="team"
                  label="Team"
                  autoFocus
                  value={formData.team}
                  onChange={handleChange}
                  error={!!errors.team}
                  helperText={errors.team}
                />
              </Grid>

              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="email"
                  label="Email Address"
                  name="email"
                  autoComplete="email"
                  value={formData.email}
                  onChange={handleChange}
                  error={!!errors.email}
                  helperText={errors.email}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  autoComplete="new-password"
                  value={formData.password}
                  onChange={handleChange}
                  error={!!errors.password}
                  helperText={errors.password}
                />
              </Grid>

            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign Up
            </Button>

            <Grid container justifyContent="flex-end">
              <Grid item>
                <Link href="/login" variant="body2">
                  Already have an account? Sign in
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
        <Copyright sx={{ mt: 5 }} />
      </Container>
    </ThemeProvider>
  );
}



