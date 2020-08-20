package cn.zyc.lambda;

/**
 * description: FutureLambda <br>
 * date: 2020/8/19 14:59 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class FutureLambda {

    /**
     * future 构建复杂并行任务
     *
     * 1 弊端 ： get方法获取值会阻塞当前线程直到获取到返回值
     */
    /*public Album lookupByName(String albumName) {
        Future<Credentials> trackLogin = loginTo("track"); 1
        Future<Credentials> artistLogin = loginTo("artist");
        try {
            Future<List<Track>> tracks = lookupTracks(albumName, trackLogin.get()); 2  这里登陆两个服务器前我们无法惊醒任何查找任务
            Future<List<Artist>> artists = lookupArtists(albumName, artistLogin.get());
            return new Album(albumName, tracks.get(), artists.get()); 3
        } catch (InterruptedException | ExecutionException e) {
            throw new AlbumLookupException(e.getCause()); 4
        }
    }*/


   /* public Album lookupByName(String albumName) {
        CompletableFuture<List<Artist>> artistLookup
                = loginTo("artist")
                .thenCompose(artistLogin -> lookupArtists(albumName, artistLogin)); 1
        return loginTo("track")
                        .thenCompose(trackLogin -> lookupTracks(albumName, trackLogin)) 2
        .thenCombine(artistLookup, (tracks, artists)
                        -> new Album(albumName, tracks, artists)) 3
        .join(); 4
    }*/

}
