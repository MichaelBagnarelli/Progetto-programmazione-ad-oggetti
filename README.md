# <div align="center">TURISTA</div>
Il progetto ha lo scopo di restiturie e analizzare i dati in formato Json relativi ai tassi di cambio delle valute. Le informazioni sui tassi di cambio vengono prelevati tramite una chiamata all'ApiREST di [currencylayer](https://currencylayer.com/) che restituisce i dati in formato Json. I dati vengono salvati in apposite classi e, dopo essere stati elaborati, vangono restituiti all'utente che li richiede

## <div align="center">DATASET</div>
Il dataset del progetto viene prelevato dall'Api di [currencylayer](https://currencylayer.com/) che restiruisce i tassi di cambio del dollaro americano rispetto ad ogni singola valuta. Nel progetto vengono prelevati due tipi di dati: attuali o storici. I dati attuali rappresentano il tasso di cambio nel momento in cui vengono richiesti mentre quelli storici riportano i dati del tasso in un giorno preciso passato. Entrambi i tipi di dati vengono modellati in classi specifiche per ogni tipo di dataset. I dati attuali vengono mappati attraverso la classe Live che contiene i seguenti campi:
* success: restiruisce l'informazione se la richiesta all'api ha avuto successo
* terms: restituisce il link per termini e condizioni di currencylayer 
* privacy: restituisce il link per la privacy di currencylayer 
* timestamp: restituisce l'esatta ora e il giorno (in formata UNIX) in cui è stata effettua la richiesta
* source: restituisce la valuta alla quale sono riferiti tutti i tassi. A causa della natura della chiamata la valuta di riferimento sarà sempre USD (dollaro americano)
* quotes: contiene tutti i valori dei tassi di cambio, costituiti dalle coppie di valute e dai rispettivi tassi di cambio.

Invece i dati storici vengono modellati dalla classe HistoricalResponse che contiene gli stessi campi della classe Historical con in più i campi:
* historical
* date: specifica la data per cui è stato richiesto il dato

## <div align="center">STRUTTURA PROGETTO</div>
Il progetto si compone della classe currencyOperations che contiene i metodi utili per l'elaborazione dei dati:
* currencyrate restituisce il tasso di cambio di qualsiasi valuta rispetto ad un'altra. Il metodo si basa sulla regola per la quale un tasso di cambio tra due valute può essere ottenuto da i tassi di cambio delle due valute rispetto ad altra valuta (USD in questo caso) e facendo la divisone tra le due.
* perdite calcola e restituisce quanto denaro viene perso (o guadagnato) durante il cambio di valuta.
* sceltaSportello restituisce lo sportello (valuta) per il quale avvengono minori perdite durante il cambio tra quelli immessi dal turista.

La classe Statistic è la classe che contiene i metodi per calcolare le statistiche su una serie di valori ottenuti per una singola valuta. Le statistiche analizate sono: la variazione della valuta, variazione percentuale, media, varianza, perdite assolute rispetto ad un'altra valuta e perdite medie.

Inoltre nel progetto è presente anche la classe Request che mappa la richiesta fatta dall'utente.

## <div align="center">RICHIESTE CONTROLLER</div>

**/data**
Restituisce tutti i dati acquisiti dall'Api in firmato Json. Si accede a questo path mediante la richiesta GET. 

**/metadata**
Restituisce tutti i metadati e i relativi tipi acquisiti dall'Api in firmato Json. Si accede a questo path mediante una richiesta GET. 

**/tasso**
Restituisce il tasso di cambio tra due valute scelte dal turista. Si accede a questo path mediante una richiesta POST. Il body richiesto deve contenere i codici delle valute che interessano all'utente per il calcolo del tasso
Esempio del body:

{

    "code" : "EUR", 
    "code2" : "USD"
    
}

**/perdita**
Restituisce la perdita durante il cambio tra due valute scelte dal turista. Si accede a questo path mediante una richiesta POST. In questo caso il body contiene oltre ai codici delle valute anche il denaro che si vuole impiegare nel cambio.
Esempio del body per il calcolo delle perdite:

{

    "code" : "EUR", 
    "code2" : "USD",
    "denaro" : 10 
    
}

**/stats**
Restituisce tutti i le statistiche di una valuta. Si accede a questo path mediante una richiesta POST. Per questa richiesta sono richieste come parametro le date di inizio e di fine per il calcolo delle statistiche mentre nel body sono contenuti i codici delle valute e il denaro.

**/sportello**
Restituisce lo sportello con minor perdita durante il cambio. Si accede a questo path mediante POST. In questo path è richiesto l'immessione dei codici degli sportelli (valute) per i quali deve essere calcolata la perdita.


<h2><div align="center">UML</div>
</p>
<h3>UseCase Diagram
</h3>
<p align="center"><img src="https://github.com/MichaelBagnarelli/Progetto-programmazione-ad-oggetti/blob/main/Uml-Turista/Case%20Diagram-Turista.png" alt style="max-width:100%;">
</p>
<h3>Class Diagram
</h3>
<p align="center"><img src="https://github.com/MichaelBagnarelli/Progetto-programmazione-ad-oggetti/blob/main/Uml-Turista/Class%20Diagramm-Turista.png" alt style="max-width:100%;">
</p>
<h3>Sequence Diagram - Scelta Sportello
</h3>
<p align="center"><img src="https://github.com/MichaelBagnarelli/Progetto-programmazione-ad-oggetti/blob/main/Uml-Turista/Sequence%20Diagram-%20Scelta%20Sportello.png" alt style="max-width:100%;">
</p>
<h3>Sequence Diagram - Statistiche
</h3>
<p align="center"><img src="https://github.com/MichaelBagnarelli/Progetto-programmazione-ad-oggetti/blob/main/Uml-Turista/Sequence%20Diagram-%20Statistiche.png" alt style="max-width:100%;">
</p>
