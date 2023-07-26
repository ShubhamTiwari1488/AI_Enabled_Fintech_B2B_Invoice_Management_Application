import React, { useState } from "react";

const AdvanceSearch = ({ onCancel, onSubmit, rows, columns }) => {
  const [searchValues, setSearchValues] = useState({});

  const handleInputChange = (event, field) => {
    const { value } = event.target;
    setSearchValues((prevValues) => ({
      ...prevValues,
      [field]: value,
    }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    onSubmit(searchValues);
  };

  return (
    <div className="advance-search-container">
      <h2>Advance Search</h2>
      <form onSubmit={handleSubmit}>
        {columns.map((column) => (
          <div key={column.field} className="search-field">
            <input
              type="text"
              id={column.field}
              value={searchValues[column.field] || ""}
              onChange={(event) => handleInputChange(event, column.field)}
              placeholder={column.headerName}
              className="advanceSearchInput"
            />
          </div>
        ))}
        <div className="search-buttons">
          <button type="submit" className="advanceSearchButton">
            Search
          </button>
          <button
            type="button"
            onClick={onCancel}
            className="advanceSearchButton"
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
};

export default AdvanceSearch;
