package rateFit;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.IntStream;

public class SongsManager {
    private Song currentSong;
    private ArrayList<Song> songs;

    public SongsManager(String songsDirectory) {
        currentSong = null;
        songs = loadSongs(songsDirectory);
    }

    public void applySongAction(int currentHr, int desiredHr, Genre favouriteGenre) {
        double heartrateRelation = (double) currentHr / desiredHr;
        Duration timeSinceSongChosen = Duration.between(Instant.now(), currentSong.getStartedAt());
        if (currentSong == null ||
                (heartrateRelation > 0.9 && heartrateRelation < 1.1 && timeSinceSongChosen.toMinutes() > 1)) {
            switchSong(favouriteGenre, desiredHr);
        }
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    private void switchSong(Genre genre, int desiredBpm) {
        Song nextSong = getNextSong(genre, desiredBpm);
        songs.remove(nextSong);
        nextSong.play();
        currentSong = nextSong;
    }

    private Song getNextSong(Genre genre, int desiredBpm) {
        Song[] songsOfGenre = songs.stream()
                .filter(s -> s.getGenre() == genre)
                .sorted(Comparator.comparing(Song::getBpm))
                .toArray(Song[]::new);

        int[] closestSongsIndexes = IntStream.range(0, songsOfGenre.length - 1)
                .dropWhile(i -> songsOfGenre[i + 1].getBpm() < desiredBpm)
                .limit(2)
                .toArray();

        Song firstSong = songsOfGenre[closestSongsIndexes[0]];
        Song secondSong = songsOfGenre[closestSongsIndexes[1]];
        if (Math.abs(firstSong.getBpm() - desiredBpm) < Math.abs(secondSong.getBpm() - desiredBpm)) {
            return firstSong;
        } else {
            return secondSong;
        }
    }

    private void changePlaybackSpeed(int desiredBpm) {

    }

    private ArrayList<Song> loadSongs(String songsDirectory) {
        ArrayList<Song> songs = new ArrayList<>();

        File dir = new File(songsDirectory);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                songs.add(new Song(child.getAbsolutePath()));
            }
        }

        return songs;
    }
}
