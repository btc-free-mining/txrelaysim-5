package sim.src;

import peersim.config.*;
import peersim.core.*;

public class PeerInitializer implements Control
{
	int pid;
    private static final String PAR_PROT = "protocol";
	
	public PeerInitializer(String prefix)
	{
		pid = Configuration.getPid(prefix + "." + PAR_PROT);
		int a  = 0;
	}
	
	
	
	@Override
	public boolean execute() 
	{
		//set the Peer pid
		Peer.pidPeer = pid;
		
		//set 0 as not peer
		((Peer)Network.get(0).getProtocol(pid)).isPeer = false;
		
		//set other peers as peer
		for(int i = 1; i < Network.size(); i++)
			((Peer)Network.get(i).getProtocol(pid)).isPeer = true;
		
		return true;
	}

}