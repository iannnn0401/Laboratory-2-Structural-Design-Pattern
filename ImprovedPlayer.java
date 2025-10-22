import java.util.*;

// ========== ADAPTER PATTERN ==========
interface MediaSource {
    void load(String fileName);
}

class LocalFilePlayer implements MediaSource {
    public void load(String fileName) {
        System.out.println("Loading local file: " + fileName);
    }
}

class HLSStreamPlayer implements MediaSource {
    public void load(String fileName) {
        System.out.println("Connecting to HLS stream: " + fileName);
    }
}

class RemoteAPIPlayer implements MediaSource {
    public void load(String fileName) {
        System.out.println("Getting remote media via API: " + fileName);
    }
}

// ========== BRIDGE PATTERN ==========
interface Renderer {
    void render(String fileName);
}

class HardwareRenderer implements Renderer {
    public void render(String fileName) {
        System.out.println("Rendering " + fileName + " using Hardware.");
    }
}

class SoftwareRenderer implements Renderer {
    public void render(String fileName) {
        System.out.println("Rendering " + fileName + " using software mode.");
    }
}

// ========== DECORATOR PATTERN ==========
interface Player {
    void play();
}

class BaseMediaPlayer implements Player {
    private String fileName;
    private Renderer renderer;

    public BaseMediaPlayer(String fileName, Renderer renderer) {
        this.fileName = fileName;
        this.renderer = renderer;
    }

    public void play() {
        renderer.render(fileName);
        System.out.println("Playing " + fileName + "...");
    }
}

abstract class PlayerDecorator implements Player {
    protected Player decoratedPlayer;

    public PlayerDecorator(Player player) {
        this.decoratedPlayer = player;
    }

    public void play() {
        decoratedPlayer.play();
    }
}

class SubtitleDecorator extends PlayerDecorator {
    public SubtitleDecorator(Player player) {
        super(player);
    }

    public void play() {
        super.play();
        System.out.println("Subtitles enabled.");
    }
}

class EqualizerDecorator extends PlayerDecorator {
    public EqualizerDecorator(Player player) {
        super(player);
    }

    public void play() {
        super.play();
        System.out.println("Equalizer effect applied.");
    }
}

class WatermarkDecorator extends PlayerDecorator {
    public WatermarkDecorator(Player player) {
        super(player);
    }

    public void play() {
        super.play();
        System.out.println("Watermark applied.");
    }
}

// ========== COMPOSITE PATTERN ==========
interface PlaylistComponent {
    void showDetails();
}

class Song implements PlaylistComponent {
    private String name;

    public Song(String name) {
        this.name = name;
    }

    public void showDetails() {
        System.out.println("Song: " + name);
    }
}

class Playlist implements PlaylistComponent {
    private String name;
    private List<PlaylistComponent> components = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public void add(PlaylistComponent component) {
        components.add(component);
    }

    public void showDetails() {
        System.out.println("Playlist: " + name);
        for (PlaylistComponent c : components) {
            c.showDetails();
        }
    }
}

// ========== PROXY PATTERN ==========
interface RemoteMedia {
    void playStream(String fileName);
}

class RealRemoteMedia implements RemoteMedia {
    public void playStream(String fileName) {
        System.out.println("Streaming remote media: " + fileName);
    }
}

class RemoteMediaProxy implements RemoteMedia {
    private RealRemoteMedia realRemoteMedia;
    private String cache;

    public void playStream(String fileName) {
        if (cache == null || !cache.equals(fileName)) {
            System.out.println("Caching remote stream for: " + fileName);
            realRemoteMedia = new RealRemoteMedia();
            cache = fileName;
        } else {
            System.out.println("Using cached version for: " + fileName);
        }
        realRemoteMedia.playStream(fileName);
    }
}

// ========== MAIN APPLICATION ==========
public class ImprovedPlayer {
    public static void main(String[] args) {

        // Simulated inputs (no Scanner)
        String sourceType = "remote"; // options: local, hls, remote
        String fileName = "ChillBeats.mp3";
        boolean useHardware = true;
        boolean enableSubtitles = true;
        boolean enableEqualizer = true;
        boolean enableWatermark = false;

        // Determine sstatus properly
        String hardwareStatus = enableSubtitles ? "Yes" : "No ";
        String subtitlesStatus = enableSubtitles ? "Applied" : "Not Applied";
        String equalizerStatus = enableEqualizer ? "Applied" : "Not Applied";
        String watermarkStatus = enableWatermark ? "Applied" : "Not Applied";
        System.out.println("=== Simulated User Configuration ===");
        System.out.println("Source Type: " + sourceType);
        System.out.println("File Name: " + fileName);
        System.out.println("Hardware Rendering: " + hardwareStatus);
        System.out.println("Subtitles: " + subtitlesStatus);
        System.out.println("Equalizer: " + equalizerStatus);
        System.out.println("Watermark: " + watermarkStatus);
        System.out.println("===================================\n");

        // Adapter pattern
        MediaSource source;
        switch (sourceType.toLowerCase()) {
            case "hls":
                source = new HLSStreamPlayer();
                break;
            case "remote":
                source = new RemoteAPIPlayer();
                break;
            default:
                source = new LocalFilePlayer();
                break;
        }
        source.load(fileName);

        // Bridge pattern
        Renderer renderer = useHardware ? new HardwareRenderer() : new SoftwareRenderer();

        // Base player
        Player player = new BaseMediaPlayer(fileName, renderer);

        // Decorator pattern (add features dynamically)
        if (enableSubtitles)
            player = new SubtitleDecorator(player);
        if (enableEqualizer)
            player = new EqualizerDecorator(player);
        if (enableWatermark)
            player = new WatermarkDecorator(player);

        // Proxy pattern for remote caching
        if (sourceType.equalsIgnoreCase("remote")) {
            RemoteMediaProxy proxy = new RemoteMediaProxy();
            proxy.playStream(fileName);
        }

        // Composite pattern - playlist example
        Playlist playlist = new Playlist("My Favorites");
        playlist.add(new Song(fileName));
        playlist.add(new Song("NextTrack.mp3"));

        Playlist mix = new Playlist("Mixed Hits");
        mix.add(new Song("Mixed Hit1.mp3"));
        mix.add(new Song("Mixed Hit2.mp3"));
        playlist.add(mix);

        System.out.println("\n--- Playlist Details ---");
        playlist.showDetails();

        System.out.println("\n--- Now Playing ---");
        player.play();

        System.out.println("");
        System.out.println("");
    }
}
