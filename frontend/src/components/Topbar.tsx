import ThemeToggle from "./ThemeToggle";

export default function Topbar() {
  return (
    <header className="flex items-center justify-end bg-white dark:bg-[#141413] text-gray-900 dark:text-gray-100 border-b border-gray-200 dark:border-gray-700 px-6 py-3 shadow-sm">
      <ThemeToggle />
    </header>
  );
}
