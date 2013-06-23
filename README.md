last4j
======

last4j is intended to be a better last.fm java client.

It does this by:
* Being easily mockable
* Taking advantage of the Spring framework.

Here's an example of how to use it:

```java
ApiKey myKey = new DefaultApiKey("your key here", "your secret key");
ArtistAPI artistClient = new ArtistApiClient();
List<TopAlbum> albums = artistClient.getTopAlbums("Brand New");
```
Simple as that.
