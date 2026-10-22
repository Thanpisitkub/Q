# Thanpisitkub Power (Forge 1.20.1)

พลังใหม่ชื่อ **thanpisitkub** มี 2 ท่า: **Galaxy Impact** (ท่าที่ 1) และ **Galaxy Divide** (ท่าที่ 2)
ใช้เสียง, particle (จาก textures ทั้ง 130 รูป), และ player animation ที่คุณให้มา

mod id: `thanpisitkub` · Minecraft 1.20.1 · Forge 47.x · Java 17

## ต้องมี
- **Player Animation Library** (KosmX/Zigy) สำหรับ Forge 1.20.1 — ใช้เล่นไฟล์ `player_animation/*.json`
  - ตอน dev: `build.gradle` ดึงจาก `https://maven.kosmx.dev/` (`pal_version` ใน `gradle.properties`)
  - ตอนเล่น: ใส่ jar ของ Player Animation Library ลงโฟลเดอร์ `mods`
  - ถ้าไม่มี ม็อดยังโหลดได้ แต่ท่าทางตัวละครจะไม่ขยับ (particle/เสียงยังทำงาน)

## Build
(ต้องใช้ **JDK 17** รัน Gradle — ตั้ง `JAVA_HOME` เป็น JDK 17)
```
./gradlew build          # ได้ไฟล์ใน build/libs/
./gradlew runClient      # ทดสอบในเกม
```

## วิธีเล่น
1. รับพลัง: ใช้ไอเท็ม **Thanpisitkub Core** (แท็บ creative "Thanpisitkub Power") คลิกขวา
   หรือ `/thanpisitkub power on [ผู้เล่น]` (op)
2. กด **Z** = Galaxy Impact · **X** = Galaxy Divide · **V** = ยกเลิกท่า (เปลี่ยนปุ่มได้ใน Controls)
3. ระหว่างใช้ท่า: เล่น animation + particle + เสียง, ตัวละครถูกล็อกการเคลื่อนที่, กล้องเป็นมุมมองบุคคลที่สาม,
   ไม่ได้รับดาเมจ (ปรับได้ใน config)

คำสั่ง (op): `/thanpisitkub cast <galaxy_impact|galaxy_divide> [ผู้เล่น]`, `/thanpisitkub cancel [ผู้เล่น]`,
`/thanpisitkub power <on|off> [ผู้เล่น]`
ทดสอบ particle เดี่ยว ๆ: `/particle thanpisitkub:garp_galaxydivide1 ~ ~2 ~`

## Config
- `config/thanpisitkub-common.toml`: cooldown, ตัวคูณดาเมจ, requirePower, invulnerableWhileCasting
- `config/thanpisitkub-client.toml`: **effectScale** (ค่าเริ่มต้น 0.25), particleDensity, lock/third-person

`effectScale = 1.0` = ตัวเลขขนาด/รัศมี/ความเร็วตามไฟล์ particle ต้นฉบับเป๊ะ ๆ (ใหญ่มาก ~50 บล็อก)
ค่าเริ่มต้น 0.25 ให้เอฟเฟกต์ขนาดพอดีกับผู้เล่น ปรับได้ตามชอบ

## ไฟล์ที่ควรรู้
| ไฟล์ | หน้าที่ |
|---|---|
| `move/Moves.java` | **ไทม์ไลน์ของทั้ง 2 ท่า** (เวลา, particle ไหน, เสียงไหน, ดาเมจ) แก้ตัวเลขได้เลย |
| `assets/.../bedrock_particles/*.json` | พฤติกรรม emitter ของ particle แต่ละชุด (โหลดใหม่ด้วย F3+T) |
| `assets/.../particles/*.json` | รายการ texture (เฟรม animation) ของ particle แต่ละชุด |
| `assets/.../player_animation/*.json` | animation ตัวผู้เล่น |
| `assets/.../sounds.json` + `sounds/*.ogg` | เสียง |

## Origins (ทางเลือก)
มีไฟล์ datapack ให้ใน `data/thanpisitkub/` (origin `thanpisitkub` + power 2 ตัวที่เรียก `/thanpisitkub cast ...`)
ใช้ได้เมื่อลงม็อด Origins/Apoli สำหรับ Forge 1.20.1 — ปุ่มความสามารถหลัก = Galaxy Impact, รอง = Galaxy Divide
(ถ้าไม่ได้ใช้ Origins ไฟล์พวกนี้ไม่มีผลอะไร)
