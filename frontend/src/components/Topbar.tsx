import ThemeToggle from "./ThemeToggle";

export default function Topbar() {
  return (
    <header className="flex items-center justify-between bg-white dark:bg-gray-900 border-b border-gray-200 dark:border-gray-700 px-6 py-3 shadow-sm">
      <h1 className="text-lg font-semibold text-gray-900 dark:text-gray-100">
        Blackjack Strategy Lab
      </h1>
      <ThemeToggle />
    </header>
  );
}
