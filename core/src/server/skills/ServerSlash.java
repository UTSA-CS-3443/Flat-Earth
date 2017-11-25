package server.skills;

import communicators.serverToClient.SkillState;
import server.ServerGameMap;
import server.entities.PosAndDir;

public class ServerSlash extends PerformableSkill {

	public ServerSlash(PosAndDir caster, ServerGameMap gameMap) {
		super(caster, 5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void perform(PosAndDir initial) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServerSkill copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SkillState getState() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
