import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import "./index.css";

// Initialize theme before React renders to prevent flash
(function initTheme() {
  try {
    let theme = localStorage.getItem("theme");
    if (!theme) {
      // Default to system preference if no saved theme
      const prefersDark = window.matchMedia("(prefers-color-scheme: dark)").matches;
      theme = prefersDark ? "dark" : "light";
      localStorage.setItem("theme", theme);
    }
    
    // Apply theme to document
    const root = document.documentElement;
    if (theme === "dark") {
      root.classList.add("dark");
    } else {
      root.classList.remove("dark");
    }
  } catch (e) {
    // Fallback: default to light mode if anything fails
    console.warn("Theme initialization failed:", e);
    document.documentElement.classList.remove("dark");
  }
})();

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
