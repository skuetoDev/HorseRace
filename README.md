[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/NOc7-NRr)



<h1> 🐴💫🐎 CARRERA DE CABALLOS 🐎💫🐴 </h1><h3>✨Dónde podras probar tu Suerte ✨</h3>
<img src="web_elements/2_SUERTE_25-08-2019.gif">
<h6>by skuetoDev</h6><hr>

<h2><code>📖 Dinámica 📖</code></h2>
<p>Para saber de qué va aqui tenemos un 
<a href="https://www.tiktok.com/@crown_ex/video/7224712441635163438">
 video
</a>
 donde se refleja a grandes rasgos el funcionamiento del juego.<br>
 Cuando aparezca un 🔢 en vez de un 🔷 quiere decir que en la parte de base de datos 🗂️ hay capturas relativas a ese instante del juego.
</p><hr>
<h2><code>🕹️ A Jugar! 🕹️</code></h2>
 
🔷 Al empezar el juego nos encontramos en la pantalla de bienvenida, dónde podemos pulsar 
![enter badge](https://img.shields.io/badge/enter-FFD700?style=for-the-badge&logoColor=white&labelColor=FFD700&color=FFD700)
para pasar al menu principal. <br>
 
<img src="web_elements/2menu.webp"><br>
 
🔷Luego ya en el menu principal seleccionaremos
![enter badge](https://img.shields.io/badge/play%20game-FFD700?style=for-the-badge&logoColor=white&labelColor=FFD700&color=FFD700)
para avanzar a la siguiente pantalla y comenzar a introducir los datos.<br>
 
<img src="web_elements/3menuPlay.webp"><br>

🔷Ahora elegiremos cuantos jugadores humanos 👨sois ( el resto serán bots🤖).<br>

<img src="web_elements/4humanPlayers.webp"><br>

🔷A continuación introducimos los nombres de usuarios humanos 👨, las apuestas 💵 y el palo de la carta a elegir entre:<br>
 + 🌿Bastos (*CLUBS*)
 + 🍷Copas (*CUPS*)
 + 🪙 Oros (*GOLD*)
 + ⚔️ Espada (*SWORDS*)

 Pulsamos 
  ![enter badge](https://img.shields.io/badge/save%20Players-FFD700?style=for-the-badge&logoColor=white&labelColor=FFD700&color=FFD700)
 Y a continuación 
 ![enter badge](https://img.shields.io/badge/next-FFD700?style=for-the-badge&logoColor=white&labelColor=FFD700&color=FFD700)

 <img src="web_elements/5data.webp"><br>
 
🔷Y nos mostrará toda la información de los 4 jugadores (🤖 &👨) asi como la cantidad que será el premio💰. Pulsamos
![enter badge](https://img.shields.io/badge/Play-FFD700?style=for-the-badge&logoColor=white&labelColor=FFD700&color=FFD700) 
y comienza el juego! 🚀

 <img src="web_elements/6InfoPlayers.webp"><br>

1️⃣ Cada 🐎 abanzará una posición cuando salga su palo desde el mazo. Exceptuando cada 5 rondas que retrocederá una posición en vez de avanzar.
 
  <img src="web_elements/8Game.webp"><br>
  
2️⃣En cualquier momento podremos abandonar la partida mediante el botón
![enter badge](https://img.shields.io/badge/Back-FFD700?style=for-the-badge&logoColor=white&labelColor=FFD700&color=FFD700) 
quedando la partida guardada en cada ronda, por si se interrumpe el programa o salimos mediante el botón mencionado.<br>

🔷Al salir del juego ( o entrar de nuevo en él), una vez nos encontremos en el menú principal, accedemos a 
![enter badge](https://img.shields.io/badge/restore%20Game-FFD700?style=for-the-badge&logoColor=white&labelColor=FFD700&color=FFD700)

<img src="web_elements/10menuRestore.webp"><br>

🔷Cada juego que no haya concluido nos aparecerá en esta pantalla pudiendo restaurarlo en este momento pulsando sobre él.<br>

<img src="web_elements/11restorableGames.webp"><br>

🔷El primer 🐎 que cruce la linea de meta gana la partida y se nos notificará que caballo ha ganado. Nos aparecerá el botón<br>
![enter badge](https://img.shields.io/badge/next-FFD700?style=for-the-badge&logoColor=white&labelColor=FFD700&color=FFD700) 
para continuar a la siguiente pantalla.<br>

<img src="web_elements/12winner.webp"><br>

3️⃣En esta pantalla se nos revelará el nombre del jugador que ha ganado y la cantidad total de dinero ganado 💸 y al pulsar el botón
![enter badge](https://img.shields.io/badge/back%20to%20menu-FFD700?style=for-the-badge&logoColor=white&labelColor=FFD700&color=FFD700)
volveremos al menu principal

<img src="web_elements/15winner.webp"><br>

🔷En el menu principal podemos consultar todos los ganadores pulsando la opción
![enter badge](https://img.shields.io/badge/show%20winners-FFD700?style=for-the-badge&logoColor=white&labelColor=FFD700&color=FFD700)

<img src="web_elements/16menuOldWinners.webp"><br>

🔷Que nos dará acceso a un registro de todos los jugadores que han ganado alguna partida 🏆 con la cantidad ganada 💰

<img src="web_elements/17winnerScreen.webp"><hr>



<h2><code>🗄️ Base de Datos 🗄️</code></h2>

1️⃣ Todos los datos de jugadores quedarán registrados por cada partida con un numero de partida 🔢 y en la columa isWinner, por defecto, 0 (no es ganador)

<img src="web_elements/7DBgame.webp"><br>

2️⃣ Cada ronda será guardada en una tabla con el numero de partida correspondiente 🗂️

<img src="web_elements/9exit.webp"><br>

3️⃣al finalizar la partida queda registrada debidamente en la base de datos.<br>

<img src="web_elements/13DBwinner.webp"><br>

Y se modifican los datos del juego para indicar que tiene jugador ganador mediante un booleano isWinner (cambiando de 0 a 1)

<img src="web_elements/14DBwinner.webp"><br><hr>

