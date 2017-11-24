package server.skills;

import server.entities.PosAndDir;

public interface Skill
{
    void perform(PosAndDir initial);

    float getPower();
}
