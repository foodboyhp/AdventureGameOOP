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
			//Set solid node
			//check tiles
			int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
			if(gp.tileM.tile[tileNum].collision == true) {
				nodes[col][row].solid = true;
			}
			
			//set cost
			getCost(nodes[col][row]);
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
	public void getCost(Node node) {
		//G cost 
		int xDistance = Math.abs(node.col-startNode.col);
		int yDistance = Math.abs(node.row-startNode.row);
		node.gCost = xDistance+yDistance;
		//H cost
		xDistance = Math.abs(node.col-goalNode.col);
		yDistance = Math.abs(node.row-goalNode.row);
		node.hCost = xDistance + yDistance;
		//Fcost
		node.fCost = node.hCost+node.gCost;
	}
	public boolean search() {
		while(goalReached==false&&step<500) {
			int col = currentNode.col;
			int row = currentNode.row;
			
			//check current node
			currentNode.checked = true;
			openList.remove(currentNode);
			
			//open the up node
			if(row-1>=0) {
				openNode(nodes[col][row-1]);
			}
			if(col-1>=0) {
				openNode(nodes[col-1][row]);
			}
			if(row+1<gp.maxWorldRow) {
				openNode(nodes[col][row+1]);
			}
			if(col+1<gp.maxWorldCol) {
				openNode(nodes[col+1][row]);
			}
			
			//find best node\
			int bestNodeIndex = 0;
			int bestNodefCost = 999;
			
			for(int i = 0; i < openList.size(); i++) {
				
				//check node's f cost
				if(openList.get(i).fCost< bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).fCost;
				}
				//if fcost is equal, check gcost
				else if(openList.get(i).fCost==bestNodefCost) {
					if(openList.get(i).gCost< openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
				}
			}
			
			//if no node in openlist, end loop
			if(openList.size()==0) {
				break;
			}
			//After loop, openlist[bestnodeindex] is the next step
			currentNode = openList.get(bestNodeIndex);
			if(currentNode==goalNode) {
				goalReached = true;
				trackThePath();
			}
			step++;
		}   
		return goalReached; 
	}
	public void openNode(Node node) {
		if(node.open == false && node.checked == false && node.solid == false) {
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
		}
	}
	public void trackThePath() {
		Node current = goalNode;
		while(current!=startNode) {
			pathList.add(0,current);
			current = current.parent;
		}
	}
}
