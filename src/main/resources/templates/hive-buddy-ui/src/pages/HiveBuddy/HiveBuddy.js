import React from 'react'
import {Box, Container, Typography} from "@mui/material";
import {PageStyles} from "./pageStyles";

const HiveBuddy = () => {
    return (
        <Box sx={PageStyles.boxHiveBuddy}>
            <Box sx={PageStyles.titleBox}>
                <Typography sx={PageStyles.typographyTitles} variant='h5'>What is Hive buddy?</Typography>
            </Box>
            <Container sx={PageStyles.container}>
                HiveBuddy is a project that focused on optimizing bee keepers work by
                collecting and processing data like temperature, humidity and noise level.
                Enhanced with Arduino sensors that allows beekeepers
                to get real-time report about their hives condition.
            </Container>
        </Box>
    )
}
export default HiveBuddy
