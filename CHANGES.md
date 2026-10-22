# Changes in this version (particles + sound swap)

Particles / sound now come from the files you sent (particles.zip, particle.zip, animasi.zip, textures.zip, sounds.zip).

**Added**
- 34 particles registered in `registry/ModParticles.java` (garp_ult1_3 ... garp_ult1_13_3, garp_ult4_1 ... garp_ult4_6),
  with `bedrock_particles/*.json`, `particles/*.json` and their frames in `textures/particle/`.
- `disc` and `box` emitter shapes (`fx/EmitterDef.java`, `client/fx/BedrockParticle.java`), used by garp_ult1_10, garp_ult1_13, garp_ult4_1 ...

**Changed**
- `move/Moves.java`: Galaxy Impact timeline now follows the particle events of your `garp_ult1.animation.json`
  (4.5 s - 16.6 s) plus the new particles; only `garp_ult1` (sounds.zip) is played for Galaxy Impact;
  Galaxy Divide plays only `garp_galaxydivide`.
- All particle frames are resized to at most 192 px so the particle atlas stays small on phones.

**Not changed**: keybinds, server logic, cooldowns (still 60 s in config / 1200 ticks in the power), networking, Origins files.
