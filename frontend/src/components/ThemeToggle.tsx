import { useEffect, useState } from "react";
import { Moon, Sun } from "lucide-react";

export default function ThemeToggle() {
  // Initialize state by reading current DOM state (set by main.tsx)
  const [darkMode, setDarkMode] = useState(() => {
    return typeof document !== "undefined"
      ? document.documentElement.classList.contains("dark")
      : false;
  });

  // Handler that directly toggles theme
  const handleToggle = () => {
    const newValue = !darkMode;
    const root = document.documentElement;

    // Update DOM immediately (before state update)
    if (newValue) {
      root.classList.add("dark");
      localStorage.setItem("theme", "dark");
    } else {
      root.classList.remove("dark");
      localStorage.setItem("theme", "light");
    }

    // Then update state (which will trigger re-render)
    setDarkMode(newValue);
  };

  // One-time sync on mount to catch any edge cases
  useEffect(() => {
    const currentDark = document.documentElement.classList.contains("dark");
    const stored = localStorage.getItem("theme");

    // If there's a mismatch, sync state to match reality
    if (stored) {
      const shouldBeDark = stored === "dark";
      if (shouldBeDark !== darkMode) {
        setDarkMode(shouldBeDark);
      }
    } else if (currentDark !== darkMode) {
      setDarkMode(currentDark);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <button
      onClick={handleToggle}
      className="p-2 rounded-md bg-gray-200 dark:bg-gray-700 hover:bg-gray-300 dark:hover:bg-gray-600 transition text-gray-900 dark:text-gray-100"
      aria-label={darkMode ? "Switch to light mode" : "Switch to dark mode"}
      type="button"
    >
      {darkMode ? <Sun className="h-5 w-5" /> : <Moon className="h-5 w-5" />}
    </button>
  );
}
