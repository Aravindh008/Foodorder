import React from 'react';
import "./mainpage.css"
import "./returnpage.css"
import { Link } from "react-router-dom";
const Mainpage = () => {
return (<>
<div>
<h1>This is Mainpage</h1>
<Link to="/returnpage" className="v2_21">Next Page</Link>
</div>
</>
);
};
export default Mainpage;