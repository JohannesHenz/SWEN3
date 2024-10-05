import React, { useState } from 'react';

function App() {
    const [count, setCount] = useState(0);

    const handleClick = () => {
        setCount(count + 1);
    };

    return (
        <div>
            <h1>Simple Counter</h1>
            <p>Current Count: {count}</p>
            <button onClick={handleClick}>Increase Count</button>
        </div>
    );
}

export default App;
