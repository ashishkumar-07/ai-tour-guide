// CityInfo.js

import React from "react";
import "./CityInfo.css";

function CityInfo({ data }) {
  const position = [
    parseFloat(data.location.latitude),
    parseFloat(data.location.longitude),
  ];

  const googleMapsLink = `https://www.google.com/maps?q=${position[0]},${position[1]}`;

  return (
    <div className="city-info">
      <h2>{data.city}</h2>
      <div className="info-container">
        <div className="info-section">
          <h3>About</h3>
          <p>{data.about}</p>
        </div>
        <div className="info-section">
          <h3>History</h3>
          <p>{data.history}</p>
        </div>
        <div className="info-section">
          <h3>Attractions</h3>
          <ul>
            {data.attraction.map((attraction, index) => (
              <li key={index}>
                <strong>{attraction.name}:</strong> {attraction.information}
              </li>
            ))}
          </ul>
        </div>
        <div className="info-section">
          <h3>Architecture</h3>
          <ul>
            {data.architecture.map((style, index) => (
              <li key={index}>{style}</li>
            ))}
          </ul>
        </div>
        <div className="info-section">
          <h3>Cuisine</h3>
          <ul>
            {data.cuisine.map((dish, index) => (
              <li key={index}>
                <strong>{dish.name}:</strong> {dish.information}
              </li>
            ))}
          </ul>
        </div>
        <div className="info-section">
          <h3>Getting Around</h3>
          <p>{data.gettingAround}</p>
        </div>
      </div>
      <div className="additional-info">
        <h3>Weather</h3>
        <p>{data.weather}</p>
        <h3>More Information</h3>

        <ul>
          {data.moreInformation.map((link, index) => (
            <li key={index}>
              <a href={link} target="_blank" rel="noopener noreferrer">
                {link}
              </a>
            </li>
          ))}
        </ul>

        <h3>Location</h3>
        <a href={googleMapsLink} target="_blank" rel="noopener noreferrer">
          View on Google Maps
        </a>
      </div>
    </div>
  );
}

export default CityInfo;
