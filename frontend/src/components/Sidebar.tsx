import { Link, useLocation } from "react-router-dom";
import { Home, Play, BarChart, User } from "lucide-react";

export default function Sidebar() {
  const location = useLocation();

  const links = [
    { name: "Dashboard", path: "/", icon: <Home size={18} /> },
    { name: "Simulate", path: "/simulate", icon: <Play size={18} /> },
    { name: "Results", path: "/results", icon: <BarChart size={18} /> },
    { name: "Account", path: "/account", icon: <User size={18} /> },
  ];

  return (
    <aside className="w-64 bg-gray-900 text-gray-100 flex flex-col">
      <div className="p-4 text-xl font-bold border-b border-gray-700">
        ♠ Blackjack Lab
      </div>
      <nav className="flex-1 p-2 space-y-1">
        {links.map((link) => (
          <Link
            key={link.name}
            to={link.path}
            className={`flex items-center gap-2 p-3 rounded-lg transition ${
              location.pathname === link.path
                ? "bg-gray-700"
                : "hover:bg-gray-800"
            }`}
          >
            {link.icon}
            <span>{link.name}</span>
          </Link>
        ))}
      </nav>
    </aside>
  );
}
