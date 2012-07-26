package sim.src;

import peersim.cdsim.CDProtocol;
import peersim.config.FastConfig;
import peersim.core.CommonState;
import peersim.core.Linkable;
import peersim.core.Network;
import peersim.core.Node;
import peersim.edsim.EDProtocol;
import peersim.transport.Transport;


public class Source implements CDProtocol, Linkable
{
	public static  int pidSource;
	
	public boolean isSource = false;
	private int packetIndex = 1;
	private int recipientIndex = 1;
	private int cycle = 1;
	
	/*
	 * Empty constructor
	 */
	public Source(String prefix){	}
	
	@Override
	public void nextCycle(Node node, int pid) 
	{
		Node recipient;
		int nextNodeIndex;
		
		if(isSource == false)
			return;
		
		System.out.println("\nCycle " + cycle +". This is SOURCE sending packet "+packetIndex+" to node "+recipientIndex+".\n");
		recipient = Network.get(recipientIndex);
		//next node in the list
		nextNodeIndex = (recipientIndex+1) % Network.size();
		if(nextNodeIndex==0)
			nextNodeIndex++;
		
		//send packet to this node, with nextNodeIndex in the resendTo field
		((Transport)recipient.getProtocol(FastConfig.getTransport(pid))).send(node, recipient, new Packet(node.getIndex(), packetIndex, nextNodeIndex), Peer.pidPeer);
		
		//for next cycle
		packetIndex++;
		recipientIndex = nextNodeIndex;
		
		cycle++;
	}

	/*
	 * Returns the regular peer with absolute index "index"
	 */
	public Peer getPeer(int index)
	{
		Node node = Network.get(index);
		//look for the Peer protocol
		for(int p = 0; p < node.protocolSize(); p++)
		{
			if(node.getProtocol(p) instanceof Peer)
				return (Peer)node.getProtocol(p);
		}
		
		return null;		
	}
	
	
	/*
	 * Other overriden methods
	 */
	@Override
	public Node getNeighbor(int index) 
	{
		// TODO Auto-generated method stub
		return null;
	}	

	
	public Object clone()
	{
		return new Source("");
	}

	@Override
	public void onKill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addNeighbor(Node arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Node arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int degree() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void pack() {
		// TODO Auto-generated method stub
		
	}

}
