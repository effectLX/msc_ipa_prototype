import React, { useEffect, useState } from "react";
import DataTable from "react-data-table-component";
import styles from "../styles/layout.module.css";
import {Button} from "@material-ui/core";

const urlToGetQueue = process.env.NEXT_PUBLIC_ADTECH_URL + "/getCurrentQueue";

const EventTable = () => {
  const [events, setEvents] = useState([]);

  const getEvents = () => {
    fetch(urlToGetQueue)
      .then((response) => response.json())
      .then((data) => setEvents(data))
      .catch((error) => console.error(error));
  };

  const columns = [
    {
      name: "Timestamp",
      selector: (row) => row.timestamp,
      id: "header1",
      width: "12rem",
      cell: row => <div style={{fontSize: 15}}>{row.timestamp}</div>
    },
    {
      name: "Event type",
      selector: (row) => row.type,
      id: "header2",
      width: "12rem",
      cell: row => <div style={{fontSize: 15}}>{row.type}</div>
    },
    {
      name: "Match Key",
      selector: (row) => row.matchKey,
      id: "header3",
      width: "12rem",
      cell: row => <div style={{fontSize: 15}}>{row.matchKey}</div>
    },
  ];

  useEffect(() => {
    getEvents();
  },[]);

  return (
    <div className={styles.container}>
      <DataTable columns={columns} data={events} pagination />
      <Button onClick={getEvents} className="button" variant="outlined">Refresh</Button>
    </div>
  );
};

export default EventTable;
