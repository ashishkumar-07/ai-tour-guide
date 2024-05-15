// App.js

import React, { useState } from 'react';
import SearchBar from './components/SearchBar';
import CityInfo from './components/CityInfo';
import Spinner from './components/Spinner';
import './App.css';

function App() {
  const [cityInfo, setCityInfo] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchCityInfo = async (city) => {
    setIsLoading(true);
    setError(null); // Clear previous error
    try {
      const response = await fetch(`http://localhost:8080/citi-info/${city}`);
      const data = await response.json();
      if (Object.keys(data).length === 0) {
        setError('city not found!');
      }
      setCityInfo(data);
    } catch (error) {
      console.error('Error fetching city information:', error);
      setError(error.message);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="App">
      <SearchBar onSearch={fetchCityInfo} />
      {isLoading ? <Spinner /> : cityInfo && <CityInfo data={cityInfo} />}
    </div>
  );
}

export default App;
