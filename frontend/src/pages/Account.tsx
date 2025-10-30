import { useState } from "react";
import { User, Lock, LogOut } from "lucide-react";

export default function Account() {
  const [form, setForm] = useState({
    username: "blackjack_master",
    email: "player@example.com",
    password: "",
    confirmPassword: "",
  });

  const [message, setMessage] = useState("");

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleSave = () => {
    if (form.password && form.password !== form.confirmPassword) {
      setMessage("Passwords do not match");
      return;
    }
    setMessage("✅ Profile updated successfully (fake save)");
  };

  const handleLogout = () => {
    setMessage("You’ve been logged out (placeholder)");
  };

  return (
    <div className="space-y-8">
      {/* Title */}
      <div>
        <h2 className="text-2xl font-semibold mb-2">Account</h2>
        <p className="text-gray-500 dark:text-gray-400">
          Manage your profile, authentication, and app preferences.
        </p>
      </div>

      {/* Profile Card */}
      <div className="bg-white dark:bg-gray-800 p-6 rounded-xl shadow-sm border border-gray-200 dark:border-gray-700">
        <div className="flex items-center space-x-4 mb-4">
          <div className="h-12 w-12 rounded-full bg-blue-600 text-white flex items-center justify-center text-xl font-bold">
            {form.username[0].toUpperCase()}
          </div>
          <div>
            <p className="text-lg font-semibold">{form.username}</p>
            <p className="text-gray-500 dark:text-gray-400">{form.email}</p>
          </div>
        </div>

        {message && (
          <div className="text-sm text-blue-600 dark:text-blue-400 mb-4">
            {message}
          </div>
        )}

        {/* Edit Form */}
        <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
          <div>
            <label className="block text-sm font-medium mb-1">Username</label>
            <input
              name="username"
              value={form.username}
              onChange={handleChange}
              className="w-full p-2 border border-gray-300 dark:border-gray-700 rounded-md bg-gray-50 dark:bg-gray-900"
            />
          </div>
          <div>
            <label className="block text-sm font-medium mb-1">Email</label>
            <input
              name="email"
              value={form.email}
              onChange={handleChange}
              className="w-full p-2 border border-gray-300 dark:border-gray-700 rounded-md bg-gray-50 dark:bg-gray-900"
            />
          </div>
          <div>
            <label className="block text-sm font-medium mb-1">New Password</label>
            <input
              name="password"
              type="password"
              value={form.password}
              onChange={handleChange}
              className="w-full p-2 border border-gray-300 dark:border-gray-700 rounded-md bg-gray-50 dark:bg-gray-900"
            />
          </div>
          <div>
            <label className="block text-sm font-medium mb-1">Confirm Password</label>
            <input
              name="confirmPassword"
              type="password"
              value={form.confirmPassword}
              onChange={handleChange}
              className="w-full p-2 border border-gray-300 dark:border-gray-700 rounded-md bg-gray-50 dark:bg-gray-900"
            />
          </div>
        </div>

        {/* Actions */}
        <div className="flex justify-between mt-6">
          <button
            onClick={handleSave}
            className="flex items-center gap-2 px-5 py-2 rounded-md bg-blue-600 hover:bg-blue-700 text-white transition"
          >
            <Lock size={16} />
            Save Changes
          </button>

          <button
            onClick={handleLogout}
            className="flex items-center gap-2 px-4 py-2 rounded-md bg-red-600 hover:bg-red-700 text-white transition"
          >
            <LogOut size={16} />
            Logout
          </button>
        </div>
      </div>

      {/* Preferences */}
      <div className="bg-white dark:bg-gray-800 p-6 rounded-xl shadow-sm border border-gray-200 dark:border-gray-700">
        <h3 className="text-lg font-semibold mb-4">Preferences</h3>
        <p className="text-gray-500 dark:text-gray-400">
          Coming soon — theme, notifications, and more.
        </p>
      </div>
    </div>
  );
}
