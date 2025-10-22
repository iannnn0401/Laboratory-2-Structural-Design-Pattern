import java.util.Scanner;

// Refactored: Preparing for Adapter and Bridge pattern integration
class LegacyMediaPlayer {
    private String sourceType;
    private String fileName;
    private boolean subtitlesEnabled;
    private boolean equalizerEnabled;
    private boolean watermarkEnabled;
    private String renderMode; // hardware or software

    public LegacyMediaPlayer() {
        subtitlesEnabled = false;
        equalizerEnabled = false;
        watermarkEnabled = false;
        renderMode = "software";
    }

    public void loadMedia(String sourceType, String fileName) {
        this.sourceType = sourceType;
        this.fileName = fileName;
        System.out.println("Loading " + sourceType + " media: " + fileName);
    }

    public void play() {
        System.out.println(
                "Playing " + fileName + " from " + sourceType + " source using " + renderMode + " rendering...");
        if (subtitlesEnabled)
            System.out.println("Subtitles are enabled.");
        if (equalizerEnabled)
            System.out.println("Audio equalizer is on.");
        if (watermarkEnabled)
            System.out.println("Watermark applied on video.");
        System.out.println("Media playing...");
    }

    public void enableSubtitles() {
        subtitlesEnabled = true;
        System.out.println("Subtitles enabled.");
    }

    public void enableEqualizer() {
        equalizerEnabled = true;
        System.out.println("Audio Equalizer enabled.");
    }

    public void enableWatermark() {
        watermarkEnabled = true;
        System.out.println("Watermark enabled.");
    }

    public void switchRenderMode(String mode) {
        renderMode = mode;
        System.out.println("Render mode switched to " + renderMode + ".");
    }

    public void simulateRemoteCaching() {
        System.out.println("Caching remote stream before playback...");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LegacyMediaPlayer player = new LegacyMediaPlayer();

        System.out.println("Enter media source type (local/hls/remote): ");
        String source = sc.nextLine();
        System.out.println("Enter file name: ");
        String file = sc.nextLine();

        player.loadMedia(source, file);

        System.out.println("Enable subtitles? (yes/no)");
        if (sc.nextLine().equalsIgnoreCase("yes"))
            player.enableSubtitles();

        System.out.println("Enable equalizer? (yes/no)");
        if (sc.nextLine().equalsIgnoreCase("yes"))
            player.enableEqualizer();

        System.out.println("Enable watermark? (yes/no)");
        if (sc.nextLine().equalsIgnoreCase("yes"))
            player.enableWatermark();

        System.out.println("Switch to hardware rendering? (yes/no)");
        if (sc.nextLine().equalsIgnoreCase("yes"))
            player.switchRenderMode("hardware");

        if (source.equalsIgnoreCase("remote"))
            player.simulateRemoteCaching();

        player.play();
        sc.close();
    }
}