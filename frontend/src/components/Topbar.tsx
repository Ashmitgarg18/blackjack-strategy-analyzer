import ThemeToggle from "./ThemeToggle";

// export default function Topbar() {
//   return (
//     <header className="h-14 bg-white shadow flex items-center justify-between px-6">
//       <h1 className="text-lg font-semibold text-gray-800">Blackjack Strategy Lab</h1>
//       <div className="text-gray-500 text-sm">v1.0.0</div>
//     </header>
//   );
// }

export default function Topbar() {
  return (
    <header className="flex items-center justify-between bg-white dark:bg-gray-900 border-b border-gray-200 dark:border-gray-700 px-6 py-3">
      <h1 className="text-lg font-semibold text-gray-900 dark:text-gray-100">
        Blackjack Strategy Lab
      </h1>

      <div className="flex items-center space-x-4">
        <ThemeToggle />
      </div>
    </header>
  );
}
