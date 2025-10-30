import { useState } from "react";
import { ArrowUpDown, Search } from "lucide-react";

interface Result {
  id: number;
  strategy: string;
  rounds: number;
  decks: number;
  ev: string;
  roi: string;
  createdAt: string;
}

export default function Results() {
  const [search, setSearch] = useState("");
  const [sortAsc, setSortAsc] = useState(true);

  const fakeResults: Result[] = [
    {
      id: 1,
      strategy: "Basic Strategy",
      rounds: 100000,
      decks: 6,
      ev: "+0.21%",
      roi: "+1.3%",
      createdAt: "2025-10-25 14:30",
    },
    {
      id: 2,
      strategy: "Aggressive Split",
      rounds: 50000,
      decks: 8,
      ev: "-0.12%",
      roi: "-0.8%",
      createdAt: "2025-10-24 20:15",
    },
    {
      id: 3,
      strategy: "Hi-Lo Counting",
      rounds: 1000000,
      decks: 6,
      ev: "+1.45%",
      roi: "+2.6%",
      createdAt: "2025-10-22 11:05",
    },
    {
      id: 4,
      strategy: "Dealer Stand 17",
      rounds: 750000,
      decks: 4,
      ev: "+0.78%",
      roi: "+1.1%",
      createdAt: "2025-10-18 16:40",
    },
  ];

  const filtered = fakeResults.filter((r) =>
    r.strategy.toLowerCase().includes(search.toLowerCase())
  );

  const sorted = [...filtered].sort((a, b) =>
    sortAsc
      ? a.strategy.localeCompare(b.strategy)
      : b.strategy.localeCompare(a.strategy)
  );

  return (
    <div className="space-y-8">
      {/* Page Title */}
      <div>
        <h2 className="text-2xl font-semibold mb-2">Results</h2>
        <p className="text-gray-500 dark:text-gray-400">
          View, filter, and analyze your past simulations.
        </p>
      </div>

      {/* Controls */}
      <div className="flex flex-col sm:flex-row justify-between items-center gap-4">
        <div className="relative w-full sm:w-64">
          <Search className="absolute left-3 top-2.5 text-gray-400 h-4 w-4" />
          <input
            type="text"
            placeholder="Search strategy..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className="w-full pl-9 pr-3 py-2 border border-gray-300 dark:border-gray-700 rounded-md bg-gray-50 dark:bg-gray-900"
          />
        </div>
        <button
          onClick={() => setSortAsc(!sortAsc)}
          className="flex items-center gap-1 text-sm px-3 py-2 border border-gray-300 dark:border-gray-700 rounded-md bg-white dark:bg-gray-800 hover:bg-gray-100 dark:hover:bg-gray-700 transition"
        >
          <ArrowUpDown size={14} />
          Sort
        </button>
      </div>

      {/* Results Table */}
      <div className="bg-white dark:bg-gray-800 rounded-xl shadow-sm border border-gray-200 dark:border-gray-700 overflow-hidden">
        <table className="w-full text-sm text-left border-collapse">
          <thead className="bg-gray-100 dark:bg-gray-700 text-gray-600 dark:text-gray-300">
            <tr>
              <th className="px-4 py-3">Strategy</th>
              <th className="px-4 py-3">Rounds</th>
              <th className="px-4 py-3">Decks</th>
              <th className="px-4 py-3">EV</th>
              <th className="px-4 py-3">ROI</th>
              <th className="px-4 py-3">Created At</th>
            </tr>
          </thead>
          <tbody>
            {sorted.map((result, i) => (
              <tr
                key={result.id}
                className={`${
                  i % 2 === 0
                    ? "bg-gray-50 dark:bg-gray-900"
                    : "bg-white dark:bg-gray-800"
                } hover:bg-gray-100 dark:hover:bg-gray-700 transition`}
              >
                <td className="px-4 py-3 font-medium">{result.strategy}</td>
                <td className="px-4 py-3">{result.rounds.toLocaleString()}</td>
                <td className="px-4 py-3">{result.decks}</td>
                <td
                  className={`px-4 py-3 ${
                    result.ev.startsWith("+")
                      ? "text-green-500"
                      : "text-red-500"
                  }`}
                >
                  {result.ev}
                </td>
                <td
                  className={`px-4 py-3 ${
                    result.roi.startsWith("+")
                      ? "text-green-500"
                      : "text-red-500"
                  }`}
                >
                  {result.roi}
                </td>
                <td className="px-4 py-3 text-gray-500 dark:text-gray-400">
                  {result.createdAt}
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        {sorted.length === 0 && (
          <div className="text-center py-6 text-gray-500 dark:text-gray-400">
            No simulations found.
          </div>
        )}
      </div>
    </div>
  );
}
