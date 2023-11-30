import './App.css';
import Navbar from "./components/Navbar/Navbar";
import HiveBuddy from "./pages/HiveBuddy/HiveBuddy";
import Process from "./pages/Process/Process";
import Strengths from "./pages/Strengths/Strengths";
import Team from "./pages/Team/Team";

function App() {
    return (
        <div className="App">
            <Navbar/>
            <HiveBuddy/>
            <Process/>
            <Strengths/>
            <Team/>
        </div>
    );
}

export default App;
