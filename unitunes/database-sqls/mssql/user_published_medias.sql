USE [unitunes]
GO

/****** Object:  Table [dbo].[USER_PUBLISHED_MEDIAS]    Script Date: 11/16/2014 20:09:06 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[USER_PUBLISHED_MEDIAS](
	[USER_ID] [bigint] NOT NULL,
	[MEDIA_ID] [bigint] NOT NULL
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[USER_PUBLISHED_MEDIAS]  WITH CHECK ADD  CONSTRAINT [FKDDEEF69474420F6] FOREIGN KEY([MEDIA_ID])
REFERENCES [dbo].[MEDIAS] ([id])
GO

ALTER TABLE [dbo].[USER_PUBLISHED_MEDIAS] CHECK CONSTRAINT [FKDDEEF69474420F6]
GO

ALTER TABLE [dbo].[USER_PUBLISHED_MEDIAS]  WITH CHECK ADD  CONSTRAINT [FKDDEEF6949BA489E] FOREIGN KEY([USER_ID])
REFERENCES [dbo].[USERS] ([id])
GO

ALTER TABLE [dbo].[USER_PUBLISHED_MEDIAS] CHECK CONSTRAINT [FKDDEEF6949BA489E]
GO

