// src/pages/DashboardPage.js
import React, { useState, useEffect } from 'react';
import LoadingSpinner from '../components/LoadingSpinner'; // Import LoadingSpinner
import { useNotification } from '../contexts/NotificationContext'; // Import useNotification
import { useAuth } from '../contexts/AuthContext'; // Import useAuth

// DashboardPage component receives theme-related dynamic classes as props
const DashboardPage = ({ textColor, subTextColor, sectionBgColor, theme, inputBgColor, inputBorderColor, inputTextColor }) => {
    const { user } = useAuth(); // Get user from AuthContext
    const { addNotification } = useNotification();
    const [dashboardStats, setDashboardStats] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);
    const [searchTerm, setSearchTerm] = useState('');

    // Simulate fetching data
    useEffect(() => {
        const fetchStats = async () => {
            setIsLoading(true);
            setError(null);
            try {
                await new Promise(resolve => setTimeout(resolve, 1800)); // Simulate network delay

                const success = Math.random() > 0.1; // 90% chance of success
                if (success) {
                    setDashboardStats([
                        { label: "Total Users", value: "1,234,567" },
                        { label: "Active Sessions", value: "8,765" },
                        { label: "Projects Completed", value: "987" },
                        { label: "Support Tickets", value: "42" },
                        { label: "Revenue (USD)", value: "$12,345,678" },
                        { label: "New Signups (Today)", value: "150" },
                    ]);
                    addNotification('Dashboard data loaded!', 'success');
                } else {
                    throw new Error('Failed to load dashboard data. Please check your connection.');
                }
            } catch (err) {
                setError(err.message);
                addNotification(`Error: ${err.message}`, 'error');
            } finally {
                setIsLoading(false);
            }
        };

        if (user) { // Only fetch if user is logged in
            fetchStats();
        } else {
            setDashboardStats([]); // Clear stats if logged out
            setIsLoading(false);
            setError('Please log in to view the dashboard.');
        }
    }, [user, addNotification]); // Re-run when user or addNotification changes

    // Filter stats based on search term
    const filteredStats = dashboardStats.filter(stat =>
        stat.label.toLowerCase().includes(searchTerm.toLowerCase()) ||
        stat.value.toLowerCase().includes(searchTerm.toLowerCase())
    );

    if (!user) {
        return (
            <div className={`text-center p-4 sm:p-6 transition-colors duration-500`}>
                <h2 className={`text-3xl sm:text-4xl font-extrabold ${textColor} mb-6`}>Dashboard Overview</h2>
                <p className={`text-xl font-semibold text-red-500`}>You must be logged in to view this page.</p>
                <p className={`text-lg ${subTextColor} mt-4`}>Please use the login button in the navigation bar.</p>
            </div>
        );
    }

    return (
        <div className={`text-center p-4 sm:p-6 transition-colors duration-500`}>
            <h2 className={`text-3xl sm:text-4xl font-extrabold ${textColor} mb-6`}>Dashboard Overview</h2>
            <p className={`text-lg ${subTextColor} mb-8`}>
                A quick glance at your application's key metrics.
            </p>

            {/* Search Bar */}
            <div className="mb-8 max-w-md mx-auto">
                <input
                    type="text"
                    placeholder="Search stats..."
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                    className={`w-full p-3 border rounded-lg shadow-sm ${inputBorderColor} ${inputBgColor} ${inputTextColor} focus:outline-none focus:ring-2 focus:ring-blue-400 text-lg transition-colors duration-500`}
                    aria-label="Search dashboard statistics"
                />
            </div>

            {isLoading ? (
                <LoadingSpinner theme={theme} />
            ) : error ? (
                <p className="text-red-500 text-xl font-semibold">{error}</p>
            ) : (
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6 max-w-5xl mx-auto">
                    {filteredStats.length > 0 ? (
                        filteredStats.map((stat, index) => (
                            <div key={index} className={`${sectionBgColor} p-6 rounded-lg shadow-md flex flex-col items-center justify-center transition-colors duration-500`}>
                                <p className={`text-5xl font-extrabold text-blue-600 mb-2`}>{stat.value}</p>
                                <p className={`text-xl font-semibold ${textColor}`}>{stat.label}</p>
                            </div>
                        ))
                    ) : (
                        <p className={`text-xl ${subTextColor} col-span-full`}>No matching statistics found.</p>
                    )}
                </div>
            )}
            <p className={`text-lg ${subTextColor} mt-8`}>
                More detailed analytics and reports are available in the full version.
            </p>
        </div>
    );
};

export default DashboardPage;
