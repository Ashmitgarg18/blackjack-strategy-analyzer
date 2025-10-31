import { useState } from "react";

export default function Simulate() {
  const [form, setForm] = useState({
    strategy: "Basic Strategy",
    decks: 6,
    rounds: 1000,
    penetration: 0.75,
  });

  const [running, setRunning] = useState(false);
  const [progress, setProgress] = useState(0);
  const [result, setResult] = useState<any | null>(null);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleRun = async () => {
    setRunning(true);
    setProgress(0);
    setResult(null);

    // fake progress bar
    const interval = setInterval(() => {
      setProgress((p) => {
        if (p >= 100) {
          clearInterval(interval);
          setRunning(false);
          setResult({
            ev: "+2.35%",
            roi: "+1.8%",
            handsPlayed: form.rounds,
            time: "1.3s",
          });
          return 100;
        }
        return p + 5;
      });
    }, 100);
  };

  return (
    <div className="space-y-8">
      {/* Title */}
      <div>
        <h2 className="text-2xl font-semibold mb-2 text-gray-900 dark:text-gray-100">Simulation</h2>
        <p className="text-gray-500 dark:text-gray-400">
          Configure your blackjack simulation and run it below.
        </p>
      </div>

      {/* Form */}
      <div className="bg-white dark:bg-gray-800 p-6 rounded-xl shadow-sm border border-gray-200 dark:border-gray-700">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div>
            <label className="block text-sm font-medium mb-2 text-gray-700 dark:text-gray-300">
              Strategy
            </label>
            <select
              name="strategy"
              value={form.strategy}
              onChange={handleChange}
              className="w-full p-2.5 border border-gray-300 dark:border-gray-600 rounded-lg bg-white dark:bg-[#141413] text-gray-900 dark:text-gray-100 focus:ring-2 focus:ring-blue-500 focus:border-blue-500 dark:focus:ring-blue-400 transition-colors cursor-pointer hover:border-gray-400 dark:hover:border-gray-500"
            >
              <option>Basic Strategy</option>
              <option>Hi-Lo Counting</option>
              <option>Aggressive Split</option>
              <option>Dealer Stand 17</option>
            </select>
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Decks</label>
            <input
              type="number"
              name="decks"
              value={form.decks}
              onChange={handleChange}
              min={1}
              max={8}
              className="w-full p-2 border border-gray-300 dark:border-gray-700 rounded-md bg-gray-50 dark:bg-[#141413] text-gray-900 dark:text-gray-100"
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Rounds</label>
            <input
              type="number"
              name="rounds"
              value={form.rounds}
              onChange={handleChange}
              min={100}
              max={1000000}
              className="w-full p-2 border border-gray-300 dark:border-gray-700 rounded-md bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-gray-100"
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">
              Penetration
            </label>
            <input
              type="number"
              step="0.05"
              min="0.1"
              max="1"
              name="penetration"
              value={form.penetration}
              onChange={handleChange}
              className="w-full p-2 border border-gray-300 dark:border-gray-700 rounded-md bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-gray-100"
            />
          </div>
        </div>

        <div className="mt-6 flex justify-end">
          <button
            onClick={handleRun}
            disabled={running}
            className={`px-5 py-2 rounded-md text-white font-medium transition ${
              running
                ? "bg-gray-400 cursor-not-allowed"
                : "bg-blue-600 hover:bg-blue-700"
            }`}
          >
            {running ? "Running..." : "Run Simulation"}
          </button>
        </div>
      </div>

      {/* Progress */}
      {running && (
        <div className="bg-white dark:bg-[#141413] p-6 rounded-xl shadow-sm border border-gray-200 dark:border-gray-700">
          <p className="mb-2 text-sm font-medium text-gray-900 dark:text-gray-100">Running Simulation...</p>
          <div className="w-full bg-gray-200 dark:bg-gray-700 rounded-full h-3 overflow-hidden">
            <div
              className="bg-blue-600 h-3 transition-all duration-200"
              style={{ width: `${progress}%` }}
            ></div>
          </div>
        </div>
      )}

      {/* Result */}
      {result && (
        <div className="bg-white dark:bg-gray-800 p-6 rounded-xl shadow-sm border border-gray-200 dark:border-gray-700 space-y-3">
          <h3 className="text-lg font-semibold mb-2 text-gray-900 dark:text-gray-100">Simulation Result</h3>
          <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
            <div>
              <p className="text-sm text-gray-500 dark:text-gray-400">
                Expected Value
              </p>
              <p className="text-lg font-medium text-gray-900 dark:text-gray-100">{result.ev}</p>
            </div>
            <div>
              <p className="text-sm text-gray-500 dark:text-gray-400">ROI</p>
              <p className="text-lg font-medium text-gray-900 dark:text-gray-100">{result.roi}</p>
            </div>
            <div>
              <p className="text-sm text-gray-500 dark:text-gray-400">
                Hands Played
              </p>
              <p className="text-lg font-medium text-gray-900 dark:text-gray-100">{result.handsPlayed}</p>
            </div>
            <div>
              <p className="text-sm text-gray-500 dark:text-gray-400">Time</p>
              <p className="text-lg font-medium text-gray-900 dark:text-gray-100">{result.time}</p>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
