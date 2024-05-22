import cors from 'cors';
import sorvete from '../src/Controllers/Sorveteria.js';
import app from '../src/index.js';

app.use(cors());

app.use(sorvete);

app.get('/',(req,res)=>
{
    res.status(200).send("Sistema de sorveteria em node js");
})
