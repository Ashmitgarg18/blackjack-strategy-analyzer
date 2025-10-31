export default function Dashboard() {
  const stats = [
    { label: "Games Played", value: "12,450" },
    { label: "Win Rate", value: "48.7%" },
    { label: "Avg Bet", value: "$52.30" },
    { label: "ROI", value: "+3.8%" },
  ];

  const simulations = [
    { id: 1, name: "Basic Strategy", result: "Win", profit: "+$230" },
    { id: 2, name: "Aggressive Split", result: "Loss", profit: "-$120" },
    { id: 3, name: "Dealer Stand 17", result: "Win", profit: "+$80" },
    { id: 4, name: "Soft 18 Hit", result: "Win", profit: "+$45" },
  ];

  return (
    <div className="space-y-8">
      {/* Page Title */}
      <div>
        <h2 className="text-2xl font-semibold mb-2 text-gray-900 dark:text-gray-100">Dashboard</h2>
        <p className="text-gray-500 dark:text-gray-400">
          Overview of your blackjack simulations.
        </p>
      </div>

      {/* Stats */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
        {stats.map((stat) => (
          <div
            key={stat.label}
            className="bg-white dark:bg-gray-800 p-5 rounded-xl shadow-sm border border-gray-200 dark:border-gray-700"
          >
            <p className="text-sm text-gray-500 dark:text-gray-400">
              {stat.label}
            </p>
            <p className="text-2xl font-semibold mt-1 text-gray-900 dark:text-gray-100">{stat.value}</p>
          </div>
        ))}
      </div>

      {/* Recent Simulations */}
      <div className="bg-white dark:bg-gray-800 rounded-xl shadow-sm border border-gray-200 dark:border-gray-700 p-6">
        <h3 className="text-lg font-semibold mb-4 text-gray-900 dark:text-gray-100">Recent Simulations</h3>
        <table className="w-full text-sm text-left">
          <thead>
            <tr className="text-gray-500 dark:text-gray-400 border-b border-gray-200 dark:border-gray-700">
              <th className="pb-2">Name</th>
              <th className="pb-2">Result</th>
              <th className="pb-2">Profit</th>
            </tr>
          </thead>
          <tbody>
            {simulations.map((sim) => (
              <tr
                key={sim.id}
                className="border-b border-gray-100 dark:border-gray-700 last:border-0"
              >
                <td className="py-2 text-gray-900 dark:text-gray-100">{sim.name}</td>
                <td
                  className={`py-2 ${
                    sim.result === "Win"
                      ? "text-green-500"
                      : "text-red-500"
                  }`}
                >
                  {sim.result}
                </td>
                <td className="text-gray-900 dark:text-gray-100">{sim.profit}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Performance Chart Placeholder */}
      <div className="bg-white dark:bg-gray-800 rounded-xl shadow-sm border border-gray-200 dark:border-gray-700 p-6 text-center text-gray-500 dark:text-gray-400">
        📊 Performance Trend (Chart Coming Soon)
      </div>
    </div>
  );
}
