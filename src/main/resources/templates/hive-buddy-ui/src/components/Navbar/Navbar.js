import React, {useState} from 'react'
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import {mainNavBarItems} from "./consts/navBarItems";
import {navBarStyles} from "./Styles"
import {useNavigate} from "react-router-dom";
import {Tabs, Tab, Typography} from '@mui/material';
import {Link, animateScroll as scroll} from "react-scroll";
import {AppBar, Box, Button, Divider,} from "@mui/material";
import {Image} from "@mui/icons-material";

const Navbar = () => {
    const navigate = useNavigate()
    const [value, setValue] = useState(0);
    return (
        <Box sx={navBarStyles.box}>
            <AppBar position="static" sx={navBarStyles.appBar}>
                <Toolbar>
                    <Box
                        component="img"
                        sx={{
                            height: 50,
                            width: 50,
                            mt: '10px',
                        }}
                        alt="Honeycomb"
                        src="https://cdn-icons-png.flaticon.com/512/5737/5737723.png "
                    />

                    <Typography sx={navBarStyles.logoText}>HiveBuddy</Typography>

                    <Tabs
                        TabIndicatorProps={{sx: {backgroundColor: "#000000"}}}
                        value={value} onChange={(e, val) => setValue(val)}
                        sx={navBarStyles.tabs}
                    >
                        {mainNavBarItems.map((item, index) => (
                            <Tab key={index} label={item.label}></Tab>
                        ))}
                    </Tabs>
                </Toolbar>
            </AppBar>
        </Box>
    )
}
export default Navbar
