<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Minimalist Weather Search</title>
  <style>
    /* Body styling with Ultra-Light Warm Gradient */
    body {
      font-family: 'Times New Roman', serif; /* Classic, clean font */
      /* Very light beige/cream gradient */
      background: linear-gradient(to bottom, #F5F5DC, #FAF0E6); 
      margin: 0;
      padding: 0;
      min-height: 100vh;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      color: #333;
    }

    h2 {
      /* Soft contrast dark text */
      color: #4B3832; 
      margin-bottom: 30px;
      letter-spacing: 3px;
      font-size: 2.5em;
      font-weight: 300; /* Lighter font weight for elegance */
      text-transform: uppercase;
    }

    /* Form styling - Crisp White Box */
    form {
      background-color: #FFFFFF;
      padding: 40px 50px;
      border-radius: 12px;
      /* Subtle, soft shadow for depth */
      box-shadow: 0 5px 15px rgba(0,0,0,0.1); 
      display: flex;
      flex-direction: column;
      align-items: center;
      border: 1px solid #E0E0E0; /* Light border for definition */
    }

    input[type="text"] {
      padding: 14px;
      width: 300px;
      border-radius: 6px;
      border: 1px solid #BCAAA4; /* Muted brown border */
      margin-bottom: 25px;
      font-size: 18px;
      color: #3e2723;
      transition: all 0.3s;
      text-align: center;
    }

    input[type="text"]:focus {
      border-color: #8D6E63; /* Warmer brown on focus */
      box-shadow: 0 0 5px rgba(141, 110, 99, 0.5);
      outline: none;
    }

    button {
      padding: 12px 30px;
      border-radius: 6px;
      border: none;
      background-color: #A1887F; /* Soft, warm brown button */
      color: #FFFFFF;
      font-size: 18px;
      cursor: pointer;
      transition: 0.3s;
      font-weight: bold;
      letter-spacing: 1px;
      box-shadow: 0 3px 10px rgba(0,0,0,0.15);
    }

    button:hover {
      background-color: #8D6E63; 
      transform: translateY(-1px);
    }
  </style>
</head>
<body>
  <h2> WEATHER FORECAST</h2>

  <form action="weather" method="get">
    <input type="text" name="city" placeholder="Enter City Name" required>
    <button type="submit">Search</button>
  </form>
</body>
</html>