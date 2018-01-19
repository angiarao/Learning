package angia.problems;

public class CircularTour {
	
	
	public int getMeACircularTourWithTheseStations(Station[] stations){
		int n = stations.length;
		int startPos = 0;
		int endPos = 1;
		int availablePetrol = stations[startPos].capacity - stations[startPos].nextStationDistance;
		while(availablePetrol<0 || endPos!=startPos){
			while(availablePetrol<0 && endPos!=startPos){
				availablePetrol = (stations[startPos].capacity+availablePetrol)-stations[startPos].nextStationDistance;
				startPos = (startPos+1)%n;
				if(startPos==0) return -1;
				
			}
			availablePetrol = (stations[endPos].capacity+availablePetrol)-stations[endPos].nextStationDistance;
			endPos = (endPos+1)%n;
		}
		
		return startPos;
	}
	
	public class Station{
		int capacity;
		int nextStationDistance;
		
		public Station(int capacity, int nextStationDistance) {
			this.capacity = capacity;
			this.nextStationDistance = nextStationDistance;
		}
	}

}
