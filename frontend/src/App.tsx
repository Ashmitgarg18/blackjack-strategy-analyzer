import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Sidebar from "./components/Sidebar";
import Topbar from "./components/Topbar";
import Dashboard from "./pages/Dashboard";
import Simulate from "./pages/Simulate";
import Results from "./pages/Results";
import Account from "./pages/Account";

function App() {
  return (
    <Router>
      <div className="flex h-screen bg-gray-100">
        <Sidebar />
        <div className="flex flex-col flex-1">
          <Topbar />
          <main className="flex-1 overflow-y-auto p-6">
            <Routes>
              <Route path="/" element={<Dashboard />} />
              <Route path="/simulate" element={<Simulate />} />
              <Route path="/results" element={<Results />} />
              <Route path="/account" element={<Account />} />
            </Routes>
          </main>
        </div>
      </div>
    </Router>
  );
}

export default App;
