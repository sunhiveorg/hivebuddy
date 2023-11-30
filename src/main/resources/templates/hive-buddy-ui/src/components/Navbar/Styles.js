export const navBarStyles = {
    drawer: {
        width: 320,
        flexShrink: 0,
        [`& .MuiDrawer-paper`]: {
            width: drawerWidth,
            boxSizing: 'border-box',
            backgroundColor: '#F4D03F',
            color: '#000000',
        },
        '&.Mui-selected': {
            color: 'red',
        }
    },
    icons: {
        color: '#B9770E',
        marginLeft: '20px',
    },
    text: {
        '& span': {
            color: '#000000',
        }
    }
}