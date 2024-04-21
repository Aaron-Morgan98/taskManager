import './styles/App.css';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import {RegisterForm} from "./pages/CreateUser";
import {Main} from "./pages/Main";
import {Header} from "./components/Header";
import {Footer} from "./components/Footer";
import {Login} from "./pages/Login";


function App() {
  return (
    <div>
        <Router>


            <Routes>
                <Route path="/" element={<Main />} />
                <Route path="/create_user" element={<RegisterForm />} />
                <Route path="/login" element={<Login />} />
            </Routes>
            <Footer />
        </Router>
    </div>
  );
};

export default App;
