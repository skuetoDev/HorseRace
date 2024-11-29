[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/NOc7-NRr)


# Bienvenido a...Carrera de caballos 🐎
![entry](/horseRace.png)
## Donde podras probar tu suerte ✨ 
by mariogarcianavas


 Para saber de va aqui tenmos un [video](https://www.tiktok.com/@crown_ex/video/7224712441635163438) donde se refleja a grandes rasgos el funcionamiento.

### Dinamica
🔷  Una vez abierto el juego debereis elegir cuantos jugadores humanos 👨 sois ( el resto serán bots🤖).  
🔷  Hecho esto, ya solo queda:
+ 🔹 Poner de cada uno de vosotros el nombre del jugador. 
+ 🔹 La apuesta que  estais dispuestos a realizar 🤑
+ 🔹 Elegir un palo de la baraja española, a saber entre Bastos🌿(*CLUBS*) Copas🍷 (*CUPS*) Oros 💰(*GOLD*) Espadas⚔️️(*SWORDS*)  

![suits](web_elements/suits.png)

y ya lo teneis... 

A countinuación se mostrará la tabla de jugadores y el juego comienza.

![players](web_elements/players.png)

En todo el proceso nos acompañará el croupier 🤵 que es quien nos habla desde el principio y quien ira sacando carta a carta 🃏.  
Cada ronda avanzará el caballo cuyo palo coincida con el de la carta sacada.  
Cada 5º ronda, el caballo no avanzará si no que retrocederá.
Si se agotaran las cartas a sacara, se volverá a barajar.

Ganará 🥇 aquel caballo que llegue al final antes, y   
Aquel jugador que haya elegido ese palo se llevará todo el bote 🏆 (Jackpot) acumulado.

### Descripción técnica

![UMLclass](/horseRaceUML.png)

Desde la clase main se inicializa la clase Croupier. Esta se encarga de usar los prints de la clase Prints mediante metodos estaticos y tambien crea los players ( usando las clases Human y Bot, la cual usa un enum para los nombres(BotName: almacena los nombres). Al crear los bots comprueba los nombres para que no salgan repetidos (Metiendo los que ya han salido en un array), al crear todos los jugadores los introduce en un ArrayList, para luego conforme se van creando no puedan repetirse los palos(suits) que ya se han elegido previamente.
Despues inicializa la clase GameLogic. La clase Croupier tambien usa los metodos estaticos de las clases Reads y Pause para leer las entradas por consola y e ir avanzado poco a poco y no todo de golpe, respectivamente.

A continuacion, al inicializarse la clase Gamelogic, crea un mazo de cartas, con la clase CardsDeck, la cual usa las clases NumeredCard (del 1 al 9) y FacedCard(cartas con figura o cara) ambas extienden de la clase abstracta Card ,usando para esta ultima el enum CardFace(almacenar las 3 figuras) y el enum CardSuit(almacenar los 4 palos).
CardsDeck crea un mazo de cartas "ordenadas" que son elegidas aleatoriamente por el método comprovarNumCartes(). Este método da un numero entero que hace referencia a la posicion en del array principal "ordenado" y la almacena si no existe.
Seguidamente, esa carta que se ha sacado se obitiene el palo y segun este, mediante el metodo gameMovement() se hace avanzar un 1(que representa al caballo) entre las diferentes posiciones que almacenadas, el resto con 0; de un array que se llama horsesPosition. Segun este array se mueve toda la logica del juego. Cada vez que se modifica una posicion le pasa una actualizacion a GameBoard( creada en esta misma clase previamente) que es la que se encarga de "dibujar"un array con la reprentacion grafica e imprimirlo.
GameBoard "dibuja" en un array la representacion grafica basada en el array horsePosition.
Tanto GameBoard como GameLogic tienen metodos que aseguran que no se salga del array evitando asi errores.
La clase GameLogic tiene un metodo que comprueba antes si hay ganador, este se averigua si el caballo se encuentra en la ultima posicion del array y sale una carta con su palo, terminando el juego.
se imprime una vez mas el juego y de esta forma se avisa que caballo ha ganado, y quien se lleva todo el jackpot de las apuestas.




