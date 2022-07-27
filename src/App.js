import Appbar from "./components/Appbar";
import Flight from "./components/Flight";
import Login from "./components/Login";
import { BrowserRouter, Routes, Route } from "react-router-dom";


function App() {
    return (

        <div className="App">

            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Login/>}>
                        <Route path="/flight" element={<Flight/>} />
                        <Route Path="/appbar" element={<Appbar />} />
                    </Route>

                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
