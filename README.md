# Project 2 - *Simple Twitter*

**Simple Twitter** is an android app that allows a user to view their Twitter timeline and post a new tweet. The app utilizes [Twitter REST API](https://dev.twitter.com/rest/public).

Time spent: **~25** hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **sign in to Twitter** using OAuth login
* [X] User can **view tweets from their home timeline**
    * [X] User is displayed the username, name, and body for each tweet
    * [X] User is displayed the [relative timestamp](https://gist.github.com/nesquena/f786232f5ef72f6e10a7) for each tweet "8m", "7h"
* [X] User can ***log out of the application** by tapping on a logout button
* [X] User can **compose and post a new tweet**
    * [X] User can click a “Compose” icon in the Action Bar on the top right
    * [X] User can then enter a new tweet and post this to Twitter
    * [X] User is taken back to home timeline with **new tweet visible** in timeline
    * [X] Newly created tweet should be manually inserted into the timeline and not rely on a full refresh
* [X] User can **see a counter that displays the total number of characters remaining for tweet** that also updates the count as the user types input on the Compose tweet page
* [X] User can **pull down to refresh tweets timeline**
* [X] User can **see embedded image media within a tweet** on list or detail view.

The following **optional** features are implemented:

* [X] User is using **"Twitter branded" colors and styles**
* [X] User sees an **indeterminate progress indicator** when any background or network task is happening
* [X] User can **select "reply" from home timeline to respond to a tweet**
    * [X] User that wrote the original tweet is **automatically "@" replied in compose**
* [X] User can view more tweets as they scroll with infinite pagination

The following **additional** features are implemented:

* [X] Add a toolbar to contain the logout and compose buttons as well as the Twitter icon
* [X] Replace some icon drawables and other static image assets with [vector drawables](http://guides.codepath.org/android/Drawables#vector-drawables) where appropriate.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

![Demo](https://github.com/orhun-kolgeli/Twitter/blob/main/Demo.gif)

## Notes

It was challenging to implement the endless scrolling feature: I needed to look up additional
query string params to use in my GET request to get different tweets from the ones that are already
in the timeline.

## Open-source libraries used

* [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
* [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright [2022] [Orhun Kolgeli]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.