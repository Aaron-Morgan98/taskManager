import './styles/App.css';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import {CreateUser} from "./pages/CreateUser";


function App() {
  return (
    <div>
        <Router>
            <Routes>
                <Route path="/create_user" element={<CreateUser />} />
            </Routes>
        </Router>
    </div>
  );
}

export default App;
