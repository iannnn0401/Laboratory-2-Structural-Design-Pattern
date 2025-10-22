# Laboratory 2: Structural Design Pattern  

## Project Overview  
This project refactors a legacy media player into a **Modular Media Streaming Suite** using **Structural Design Patterns** in Java.  
It demonstrates modularity, flexibility, and scalability through:  
- Multiple media sources (Local, HLS, Remote API)  
- Dynamic feature plugins (Subtitles, Equalizer, Watermark)  
- Composite playlists  
- Switching between hardware/software rendering  
- Caching of remote streams  

---

## Implemented Design Patterns  

 Pattern   | Purpose                                      | Example in Code 
-----------|----------------------------------------------|----------------------------------------------------------------
 Adapter   | Unifies different media sources              | `LocalFilePlayer`, `HLSStreamPlayer`, `RemoteAPIPlayer` 
 Bridge    | Switches between hardware/software rendering | `Renderer`, `HardwareRenderer`, `SoftwareRenderer` 
 Decorator | Adds dynamic features                        | `SubtitleDecorator`, `EqualizerDecorator`, `WatermarkDecorator` 
 Composite | Nested playlists and song hierarchy          | `Playlist`, `Song` 
 Proxy     | Caches and reuses remote streams             | `RemoteMediaProxy`, `RealRemoteMedia` 

---

## Repository Structure  

integ2Lab2/
│
├── src/
│ └── ImprovedMediaPlayer.java # Main program file
│
├── legacy_code/
│ └── LegacyMediaPlayer.java # Provided legacy version
│
├── docs/
│ ├── design_rationale.docx
│ ├── diagram_structural.docx
│ ├── sequence_diagram.docx
│ └── architecture_overview.docx
│
└── README.md


---

# How to Run the Program

1. Clone the repository:
   bash
   git clone https://github.com/kenUZi/integ2Lab2.git
   cd integ2Lab2/src

2. Compile the program:
   javac ImprovedPlayer.java


3. Run the program:
   java ImprovedPlayer


4. Follow the on-screen prompts:
   - Choose media source (local, hls, remote)
   - Select rendering mode (hardware, software)
   - Enable optional features (subtitles, equalizer, watermark)
   - Observe proxy caching behavior when using remote streaming

---

Author

James B. Blanco
BSIT
Integrative Programming 2
Laboratory 2: Structural Design Pattern