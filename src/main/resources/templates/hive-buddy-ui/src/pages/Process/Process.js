import React, {useEffect, useState} from 'react'
import axios from "axios";
import {DataGrid} from '@mui/x-data-grid';


const baseURL = "http://localhost:8080/api/v1/data";
const Process = () => {
    const [data, setData] = useState([]);
    useEffect(() => {
        axios.get(baseURL).then((response) => {
            setData(response.data[1]);
        });
    }, []);

    const columns = [
        {field: 'id', headerName: 'ID', width: 70},
        {field: 'value', headerName: 'Sensor value', width: 130},
        {field: 'time', headerName: 'Timestamp', width: 130},
        // {
        //     field: 'time',
        //     headerName: 'Timestamp',
        //     description: 'This column has a value getter and is not sortable.',
        //     sortable: false,
        //     width: 160,
        // valueGetter: (params) =>
        //     `${params.row.firstName || ''} ${params.row.lastName || ''}`,
        // },
    ];
    // const rows = [
    //     {id: data.id, value: data.value, time: data.timestamp}
    // ];
    return (
        <div style={{height: 400, width: '100%'}}>
            <DataGrid
                rows={{id: data.id, value: data.value, time: data.timestamp}}
                columns={columns}
                initialState={{
                    pagination: {
                        paginationModel: {page: 0, pageSize: 5},
                    },
                }}
                pageSizeOptions={[5, 10]}
                // checkboxSelection
            />
        </div>
        // <div>
        //     <h1>{data.id}</h1>
        //     <h1>{data.value}</h1>
        //     <h1>{data.sensor_type_id}</h1>
        //     <h1>{data.timestamp}</h1>
        // </div>

    );
};
export default Process
