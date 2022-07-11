package ai;

import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;

public class PathFinder {
	
	GamePanel gp;
	Node[][] nodes;
	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	Node startNode,goalNode,currentNode;
	boolean goalReached;
	int step = 0;
	public PathFinder(GamePanel gp) {
		this.gp = gp;
		initNodes();
	}
	public void initNodes() {
		nodes = new Node[gp.maxWorldCol][gp.maxWorldRow];
		int col = 0;
		int row = 0;
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			nodes[col][row] = new Node(col, row);
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
	public void resetNodes() {
		int col = 0;
		int row = 0;
		while(col< gp.maxWorldCol && row<gp.maxWorldRow) {
			nodes[col][row].open = false;
			nodes[col][row].checked = false;
			nodes[col][row].solid = false;
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
		//reset other settings
		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;
	}
	public void setNodes(int startCol, int startRow, int goalCol, int goalRow, Entity entity) {
		resetNodes();
		//setup
		startNode = nodes[startCol][startRow];
		currentNode = startNode;
		goalNode = nodes[goalCol][goalRow];
		openList.add(currentNode);
		
		int col = 0;
		int row = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
		}
	}
}
